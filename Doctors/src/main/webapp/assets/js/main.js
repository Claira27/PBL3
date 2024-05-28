
//for scroll header transition
window.onscroll = function() {
    scrollFunction();
};

function scrollFunction() {
    var header = document.getElementById("myHeader");
    if (document.body.scrollTop > 334 || document.documentElement.scrollTop > 334) {
        header.classList.add("scrolled");
    } else {
        header.classList.remove("scrolled");
    }
}


//document load
let temporaryData = null;
document.addEventListener('DOMContentLoaded', () => {

    // Fetch data and populate selects
    fetch('taidulieu')
    .then(response => {
    	if (!response.ok) {
        	throw new Error('Network response was not ok');
    	}
    return response.json();
    })
    .then(data => {
        console.log(data);
        renderDataToUI(data);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
});
 // Hàm để render dữ liệu lên giao diện người dùng
function renderDataToUI(data) {
    // Ví dụ: render dữ liệu lên các select options
    const areaSelect = document.getElementById('area');
    const hospitalSelect = document.getElementById('hospital');
    const specialtySelect = document.getElementById('specialty');

    data.provinces.forEach(area => {
        let option = document.createElement('option');
        option.value = area.id;
        option.textContent = area.ten;
        areaSelect.appendChild(option);
        option.classList.add('search-filter__area-value');
    });

    data.hospitals.forEach(hospital => {
        let option = document.createElement('option');
        option.value = hospital.id;
        option.textContent = hospital.ten;
        hospitalSelect.appendChild(option);
        option.classList.add('search-filter__area-value');
    });

    data.specialties.forEach(specialty => {
        let option = document.createElement('option');
        option.value = specialty.id;
        option.textContent = specialty.ten;
        specialtySelect.appendChild(option);
        option.classList.add('search-filter__area-value');
    });
}

