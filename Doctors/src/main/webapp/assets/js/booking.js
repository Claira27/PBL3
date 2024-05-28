const urlParams = new URLSearchParams(window.location.search);
const doctorId = urlParams.get('doctorId');
const service = urlParams.get('service');
const date = urlParams.get('date');
const time = urlParams.get('time');

console.log(doctorId + " " + service + " "+date + " " + time);
function fetchData(){
    fetch(`taodatlich?doctorId=${doctorId}&service=${service}&date=${date}&time=${time}`, {
        method: 'GET',
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        //fill form with data
        renderData(data);
        
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();

function renderData(data){
	const date = document.querySelector('.date-info');
	const time = document.querySelector('.time-info');
	const photo = document.querySelector('.doctor-item__image');
	const name = document.querySelector('.booking-doctor-name');
	const unit = document.getElementById('booking-hospital');
	const specialty = document.getElementById('speciality-name');
	const fee = document.getElementById('booking-fee');
	
	//fill with data
	date.textContent = `${data.datename}`;
	time.textContent = `${data.time}${data.part} `;
	name.textContent = `${data.doctor.name}`;
	
	unit.textContent = `${data.doctor.unit}`;
	
	let specialtyText = data.doctor.specialties.map(specialty => specialty).join(', ');
	
	specialty.textContent = specialtyText;
	
	photo.src = data.doctor.imageUrl;
	
	if(service == "0"){
		fee.textContent = "Miễn phí";
	}else {
		fee.textContent = `${data.doctor.giadichvu}.000 vnd`;
	}
}

//cancel or finish booking
const btn_change = document.querySelector('.booking-change-text');
btn_change.addEventListener('click', function() {
	window.history.back();
});
const btn_cancel = document.getElementById('booking-cancel');
btn_cancel.addEventListener('click', function() {
	window.location.href='index.jsp';
});
const btn_pay = document.getElementById('booking-pay');
btn_pay.addEventListener('click', function(){
	
	fetch(`luudatlich?doctorId=${doctorId}&typeid=${service}&date=${date}&time=${time}`, {
		method:"GET",
		headers: {"Content-type": "application/json; charset=UTF-8"}
	})
	.then(response => response.json())
	.then(data => {
		console.log(data);
		if(data.success == "1"){
			let confirm = document.querySelector('.confirm-site');
			confirm.style.display = 'none';
			displaySuccessBooking(data);
		}else{
			alert('luu dat lich hen khong thanh cong');
		}
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
});
		
function getSuccessBooking(data){
    let servicehtml ='';
    if(data.service == "1"){
        servicehtml = `<div class="booking-others" id="booking-hospital">Dịch vụ: Tư vấn trực tuyến</div>
                            <div class="booking-others" id="booking-address">Phí: ${data.doctor.giadichvu}.000vnd</div>`;
    } else {
        servicehtml = `<div class="booking-others" id="booking-hospital">Khám tại: ${data.doctor.unit}</div>
                            <div class="booking-others" id="booking-address">Phí: miễn phí</div>`;
    }
    return `
    <div class="confirm-site">
        <div class="booking-confirm">
            <div class="success-info">
                <div class="booking-success">
                    <div class="">
                        <i class="fa-solid fa-stethoscope booking-icon"></i>
                        <span class="success-msg">BẠN ĐÃ ĐẶT LỊCH KHÁM THÀNH CÔNG</span>
                    </div>
                    <div class="">
                        <p class="success-doctor-name">${data.doctor.name}</p>
                    </div>
                </div>
                <div class="booking-info-item">
                    <div class="booking-time">
                        <div class="booking-date">
                            <i class="fa-solid fa-calendar-week booking-date-icon"></i>
                            <span class="booking-at-time">${data.datename}</span>
                        </div>
                        <div class="booking-at">
                            <i class="fa-regular fa-clock booking-at-icon"></i>
                            <span class="booking-at-time">${data.time}${data.part}</span>
                        </div>
                    </div>
                    <div class="booking-contain-item">
                        <i class="fa-solid fa-notes-medical booking-others"></i>
                        <div class="booking-others" id="speciality-label">Chuyên Khoa: </div>
                        <div class="booking-others" id="speciality-name">${data.doctor.specialties[0]}</div>
                    </div>
                    <div class="booking-contain-item">
                        <i class="fa-regular fa-hospital booking-others"></i>
                        ${servicehtml} 
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
}

function displaySuccessBooking(data){
	let background = document.querySelector('.background-wrap');
	background.innerHTML += getSuccessBooking(data);
	setTimeout(function() {
        background.innerHTML = ''; 
        window.location.href = 'index.jsp';
    }, 30000); 
}
		
//control session
setInterval(function() {
    // Gửi một yêu cầu AJAX đến một endpoint trên server để kiểm tra session
    fetch('kiemtranguoidung')
        .then(response => response.json())
        .then(result => {
			//console.log(result);
            // Kiểm tra kết quả từ server
            if (result == "1") {
            } else if (result == "0") {
                window.location.reload();
            } else {
                console.error('Kết quả không hợp lệ từ server');
            }
        })
        .catch(error => {
            console.error('Lỗi kiểm tra session:', error);
        });
}, 60000);
