/**
 * 
 */
//fetch and paginate doctors

function fetchDoctors(currentPage){
	let doctorList = document.querySelector('.doctor-list');
	doctorList.innerHTML='';
	
	fetch('taibacsy?page=' + currentPage)
    .then(response =>  response.json())
    .then(data => {
        console.log(data);
        //tai du lieu bac sy
        updatePagination(data.totalPage, data.currentPage);
		data.doctors.forEach(doctor => {
		    const doctorHTML = createDoctorHTML(doctor, data.isdoctor);
		    doctorList.innerHTML += doctorHTML;
		});
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
}
fetchDoctors(1);

const pageNavigation = document.querySelector('.page-navigation');
const currentPageElement = document.querySelector('.btn__page-current');

function updatePagination(totalPages, currentPage) {
    const maxButtonsToShow = 3; // Số lượng nút phân trang giữa tối đa được hiển thị
    const pageNavigation = document.querySelector('.page-navigation');

    // Xóa tất cả các nút phân trang cũ
    pageNavigation.innerHTML = '';

    // Tạo nút "Prev" (nếu currentPage không phải là trang đầu tiên)
    if (currentPage > 1) {
        createPrevButton();
    }

    // Tạo nút trang đầu tiên
    createPageButton(1, currentPage === 1);

    // Tạo nút phân trang giữa (nếu có)
    const startPage = Math.max(2, Math.min(currentPage - Math.floor(maxButtonsToShow / 2), totalPages - maxButtonsToShow + 1));
    const endPage = Math.min(startPage + maxButtonsToShow - 1, totalPages);
    for (let i = startPage; i <= endPage; i++) {
        createPageButton(i, currentPage === i);
    }

    // Tạo nút trang cuối cùng (nếu có quá nhiều trang)
    if (totalPages > maxButtonsToShow) {
        createPageButton(totalPages, currentPage === totalPages);
    }

    // Tạo nút "Next" (nếu currentPage không phải là trang cuối cùng)
    if (currentPage < totalPages) {
        createNextButton();
    }

    function createPageButton(pageNumber, isActive) {
        const button = document.createElement('span');
        button.innerText = pageNumber;
        button.classList.add('btn__page-navigation');
        if (isActive) {
            button.classList.add('btn__page-current');
        }
        button.addEventListener('click', function() {
            fetchDoctors(pageNumber);
        });
        pageNavigation.appendChild(button);
    }

    function createPrevButton() {
        const prevButton = document.createElement('span');
        prevButton.innerText = '<';
        prevButton.classList.add('btn__page-navigation');
        prevButton.addEventListener('click', function() {
            fetchDoctors(currentPage - 1);
        });
        pageNavigation.appendChild(prevButton);
    }

    function createNextButton() {
        const nextButton = document.createElement('span');
        nextButton.innerText = '>';
        nextButton.classList.add('btn__page-navigation');
        nextButton.addEventListener('click', function() {
            fetchDoctors(currentPage + 1);
        });
        pageNavigation.appendChild(nextButton);
    }
}

//hàm tạo html bác sỹ
function createDoctorHTML(doctor, isdoctor) {
    const specialtiesHTML = doctor.specialties.map(specialty => `
        <li class="specialty__name">
            <a href="" class="specialty__name-link">${specialty}</a>
        </li>
    `).join('');

    let contactButtonsHTML = '';
    if(isdoctor === 0){
		if (doctor.dichvu === 1) {
	        // If doctor.dichvu is 1, display both appointment booking and online consultation buttons
	        contactButtonsHTML = `
	            <button id="btn__doctor-book" class="btn__doctor-contact btn__doctor-book">
	                <i class="fa-regular fa-calendar-days"></i>
	                Đặt lịch hẹn
	            </button>
	            <button id="btn__doctor-consult" class="btn__doctor-contact btn__doctor-consult">
	                <i class="fa-brands fa-rocketchat"></i>
	                Tư vấn trực tuyến
	            </button>
	        `;
	    } else {
	        // If doctor.dichvu is not 1, display only appointment booking button
	        contactButtonsHTML = `
	            <button id="btn__doctor-book" class="btn__doctor-contact btn__doctor-book">
	                <i class="fa-regular fa-calendar-days"></i>
	                Đặt lịch hẹn
	            </button>
	        `;
	    }
	}
	if(isdoctor === 1) {
		if (doctor.dichvu === 1) {
	        // If doctor.dichvu is 1, display both appointment booking and online consultation buttons
	        contactButtonsHTML = `
	            
	        `;
	    } else {
	        // If doctor.dichvu is not 1, display only appointment booking button
	        contactButtonsHTML = `
	            
	        `;
	    }
	}
    return `
        <li id="${doctor.id}" class="doctor-item">
            <a href="" class="doctor-item-link">
                <img src="${doctor.imageUrl}" alt="Doctor photo" class="doctor-item__image">
            </a>
            <div class="doctor-item__sumary">
                <div class="doctor__name">
                    <a href="" class="doctor-link">${doctor.name}</a>
                </div>
                <span class="doctor-item__title">${doctor.title}</span>
                <div class="doctor-item__unit">
                    <p class="doctor-item-label">
                        Đơn vị: 
                        <a href="" class="doctor-item-name doctor-item-name-link">${doctor.unit}</a>
                    </p>
                </div>
                <div class="doctor-item__experience">
                    <p class="doctor-item-label">
                        Kinh nghiệm:
                        <span class="doctor-item-name">${doctor.experience}+ năm</span>
                    </p>
                </div>
                <ul class="doctor-item__specialty">
                    ${specialtiesHTML}
                </ul>
            </div>
            <div class="doctor-contact">
                ${contactButtonsHTML}
                <div class="doctor-item__rating">
                    <div class="rating-star">
                        ${'<i class="fa-solid fa-star star-checked"></i>'.repeat(doctor.rating.stars)}
                        ${'<i class="fa-solid fa-star"></i>'.repeat(5 - doctor.rating.stars)}
                    </div>
                    <span class="rating-info">(Đánh giá: ${doctor.rating.stars}/5 dựa trên ${doctor.rating.totalRatings} lượt đánh giá)</span>
                </div>
            </div>
        </li>
    `;
}


// Thêm sự kiện click cho nút đặt lịch hẹn và nút tư vấn trực tuyến
document.addEventListener('click', function(event) {
	 let doctorService = 0;
    if (event.target.id === 'btn__doctor-book') {
        const doctorItem = event.target.closest('.doctor-item');
        const doctorItemId = doctorItem.getAttribute('id');
        doctorService = 0;// Service for booking
        // Gọi API và truyền dữ liệu của bác sĩ khi đặt lịch hẹn
        bookAppointment(doctorItemId, doctorService);
    } else if (event.target.id === 'btn__doctor-consult') {
        const doctorItem = event.target.closest('.doctor-item');
        const doctorItemId = doctorItem.getAttribute('id');
        doctorService = 1; // Service for online consultation
        // Gọi API và truyền dữ liệu của bác sĩ khi tư vấn trực tuyến
        bookAppointment(doctorItemId, doctorService);
    }
});

// Hàm gọi API để đặt lịch hẹn
function bookAppointment(doctorId, service) {
	console.log(doctorId + ", " + service);
	const url = `datlich.jsp?doctorId=${doctorId}&service=${service}`;
	window.location.href = url;
}

//search doctors
var btnSearch = document.getElementById("btn_search");
btnSearch.addEventListener('click', searchWithFilter);
function searchWithFilter(){
	const doctorList = document.querySelector('.doctor-list');
	const area = document.getElementById('area').value;
	const hospital = document.getElementById('hospital').value;
	const specialty = document.getElementById('specialty').value;
	const doctorname = document.getElementById('doctorname').value;
	doctorList.innerHTML = '';
	console.log(temporaryData);
	fetch('timkiem',{
		method: 'POST',
		body: JSON.stringify({
			area:area,
			hospital:hospital,
			specialty:specialty,
			doctorname:doctorname
		}),
		headers: {"Content-type": "application/json; charset=UTF-8"}
	})
	.then(response => response.json())
	.then(data => {
		//tai du lieu bac sy
		console.log(data);
        //tai du lieu bac sy
        updatePagination(data.totalPage, data.currentPage);
		data.doctors.forEach(doctor => {
		    const doctorHTML = createDoctorHTML(doctor, data.isdoctor);
		    doctorList.innerHTML += doctorHTML;
		});
	})
	.catch(error => {
         console.error('Error fetching data:', error);
    });
}

setInterval(function() {
    // Gửi một yêu cầu AJAX đến một endpoint trên server để kiểm tra session
    fetch('kiemtranguoidung')
        .then(response => response.json())
        .then(result => {
			//console.log(result);
            // Kiểm tra kết quả từ server
            if (result == "1") {
				console.log("session is still");
            } else if (result == "0") {
                window.location.reload();
            } else {
                console.error('Kết quả không hợp lệ từ server');
            }
        })
        .catch(error => {
            console.error('Lỗi kiểm tra session:', error);
        });
}, 600000);
