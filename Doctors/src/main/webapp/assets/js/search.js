//Thuc hien thao tac tim kiem bac sy theo bo loc: area, hospital, specialty, name of doctor
var areaSelect = document.getElementById("area");
var hospitalSelect = document.getElementById("hospital");
var specialtySelect = document.getElementById("specialty");

areaSelect.addEventListener("change", function() {
    updateSelect(areaSelect);
});
hospitalSelect.addEventListener("change", function() {
    updateSelect(hospitalSelect);
});
specialtySelect.addEventListener("change", function() {
    updateSelect(specialtySelect);
});

function updateSelect(selectElement) {
    var selectedIndex = selectElement.selectedIndex;
    var selectedOption = selectElement.options[selectedIndex];
    var selectedValue = selectedOption.value;

    // Loop through all options to remove "selected" attribute
    for (var i = 0; i < selectElement.options.length; i++) {
        selectElement.options[i].removeAttribute('selected');
    }

    // Set "selected" attribute for the selected option
    selectedOption.setAttribute('selected', 'selected');

    // Update select immediately
    selectElement.value = selectedValue;
    selectElement.blur();
}

areaSelect.onchange = updateHospitals;

function updateHospitals() {
    var area = document.getElementById("area").value;
    if(area){
        fetch('laybenhvientheotinhthanh?area=' + area)
        .then(response => response.json())
        .then(data => {
			console.log(data);
            hospitalSelect.innerHTML = '<option value="" selected disabled>Chọn bệnh viện</option>';
            specialtySelect.innerHTML = '<option value="" selected disabled>Chọn chuyên khoa</option>';
            data.forEach(hospital => {
                hospitalSelect.innerHTML += `<option class="search-filter__area-value" value="${hospital.id}">${hospital.ten}</option>`;
            });
        })
        .catch(error => console.error('Error:', error));
    }
}

hospitalSelect.onchange = updateSpecialties;

function updateSpecialties() {
    var hospital = document.getElementById("hospital").value;
    if(hospital){
        fetch('laychuyenkhoatheobenhvien?hospital=' + hospital)
        .then(response => response.json())
        .then(data => {
			console.log(data);
            specialtySelect.innerHTML = '<option value="" selected disabled>Chọn chuyên khoa</option>';
            data.forEach(specialty => {
                specialtySelect.innerHTML += `<option class="search-filter__area-value" value="${specialty.id}">${specialty.ten}</option>`;
            });
        })
        .catch(error => console.error('Error:', error));    
    }
}

