const urlParams = new URLSearchParams(window.location.search);
const doctorId = urlParams.get('doctorId');
const service = urlParams.get('service');

console.log(doctorId + " " + service);
var TIMEINFO = null;
function fetchTime(id_date, service){
    fetch(`thoigiandatlich?doctorId=${doctorId}&service=${service}`, {
        method: 'GET',
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        //tai len bac sy
        renderDoctor(data.doctor, service);
        //tai len lich kham
        renderTimeData(data.timeInfo, id_date);
       	pickDateAndTime(data.timeInfo);
       	pickTime();

       	confirmInfo();
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}

function renderDoctor(data, service){
	const doctoritem = document.querySelector('.doctor-item--result');
	const doctorLink = document.querySelector('.doctor-link');
    const doctorImageUrl = document.querySelector('.doctor-item__image');
    const doctorExperience = document.getElementById('kinhnghiem');
    const doctorUnit = document.getElementById('unit');
    const specialtyList = document.querySelector('.doctor-item__specialty');
    const ratingStars = document.querySelector('.rating-star');
    const ratingInfo = document.querySelector('.rating-info');
	const typeid = document.querySelector('.booking-contain-type');
	const fee = document.getElementById('booking-fee');
	fee.innerHTML = '';
	
	//them id bac sy 
	if (doctoritem) {
	    doctoritem.id = `${data.id}`;
	}
    // Cập nhật nội dung của các phần tử HTML với dữ liệu từ API
    doctorLink.textContent = `${data.name}`;
    doctorImageUrl.src = data.imageUrl;
    doctorUnit.textContent = `${data.unit}`;
	doctorExperience.textContent = `${data.experience}+ năm`
    // Xóa các chuyên khoa hiện có để cập nhật lại
    specialtyList.innerHTML = '';

    // Thêm các chuyên khoa mới
    data.specialties.forEach(specialty => {
        const specialtyItem = document.createElement('li');
        specialtyItem.classList.add('specialty__name');
        specialtyItem.innerHTML = `<a href="" class="specialty__name-link">${specialty}</a>`;
        specialtyList.appendChild(specialtyItem);
    });

    // Xác định số sao đánh giá
    const starsChecked = Math.floor(data.rating.stars);
    const starsUnchecked = 5 - starsChecked;

    // Cập nhật số sao đánh giá
    ratingStars.innerHTML = '';
    for (let i = 0; i < starsChecked; i++) {
        const starChecked = document.createElement('i');
        starChecked.classList.add('fa-solid', 'fa-star', 'star-checked');
        ratingStars.appendChild(starChecked);
    }
    for (let i = 0; i < starsUnchecked; i++) {
        const starUnchecked = document.createElement('i');
        starUnchecked.classList.add('fa-solid', 'fa-star');
        ratingStars.appendChild(starUnchecked);
    }

    // Cập nhật thông tin về đánh giá
    ratingInfo.textContent = `(Đánh giá: ${data.rating.stars}/5 dựa trên ${data.rating.totalRatings} lượt đánh giá)`;
	
	if(service == "0"){
		document.getElementById("offline").checked = true;
		typeid.id = '0';
		fee.innerHTML = 'Miễn phí';
	}else {
		document.getElementById("online").checked = true;
		typeid.id = '1';
		fee.innerHTML = `${data.giadichvu}.000vnd`;
	}
}

//pick service type
const radioButtons = document.querySelectorAll('.type-radi-btn');
radioButtons.forEach(button => {
    button.addEventListener('click', function() {
        // Lấy giá trị của nút radio được chọn
        const bookingType = this.value;
        
        const typeid = document.querySelector('.booking-contain-type');
		
        if (bookingType === 'online') {
			document.getElementById("online").checked = true;
			typeid.id = '1';
        } else if (bookingType === 'offline') {
			document.getElementById("offline").checked = true;
			typeid.id = '0';
        }
		fetchTime(0, typeid.id);
    });
});
		
function renderTimeData(data, id_date){
	let date_cards = document.querySelector(".date-cards");
	date_cards.innerHTML = '';
	for(let i = 0; i < data.length; i++){
		date_cards.innerHTML += createDate(data[i], i, id_date);
	}
	createTime(data, id_date);
}
function createDate(data, index, id_date){
	if(index == id_date){
		return `
        <button class="date-card active" id="${data.date.id}">
            <p class="date-value">${data.date.time}</p>
            <p class="slot-avail">${data.morning.length + data.afternoon.length} lịch trống</p>
        </button>
    `;
	}else{
		return `
        <button class="date-card" id="${data.date.id}">
            <p class="date-value">${data.date.time}</p>
            <p class="slot-avail">${data.morning.length + data.afternoon.length} lịch trống</p>
        </button>
    `;
	}
}
function createTime(data, id_date){
	let date = data[id_date];
	let morningslots = document.getElementById("morning");
	let afternoonslots = document.getElementById("afternoon");
	morningslots.innerHTML='';
	afternoonslots.innerHTML='';
	date.morning.forEach(slot => {
        morningslots.innerHTML += `
            <button class="slot-card">
                <span id="${slot.id}" class="slot-time">${slot.time}</span>
            </button>
        `;
    });
    
    date.afternoon.forEach(slot => {
        afternoonslots.innerHTML += `
            <button class="slot-card">
                <span id="${slot.id}" class="slot-time">${slot.time}</span>
            </button>
        `;
    });
}
//first fetch for page loading
fetchTime(0, service);
//pick a time scipt
function pickDateAndTime(data){
	const prevBtn = document.getElementById('prev-btn');
	const nextBtn = document.getElementById('next-btn');
	const dateBar = document.querySelector('.date-cards');
	const dateCards = document.querySelectorAll('.date-card');
	let currentIndex = 0;
	dateCards[currentIndex].classList.add('active');
	prevBtn.addEventListener('click', function() {
	    if (currentIndex > 0) {
	        currentIndex--;
	        updateVisibleCards(data,currentIndex);
	    }
	});
	
	nextBtn.addEventListener('click', function() {
	    if (currentIndex < dateCards.length - 1) {
	        currentIndex++;
	        updateVisibleCards(data,currentIndex);
	    }
	});
	
	dateCards.forEach((dateCard, index) => {
	    dateCard.addEventListener('click', function(event) {
	        // Lấy ra chỉ số của dateCard được click
	        const clickedIndex = Array.from(dateCards).indexOf(event.currentTarget);
	        const activeCard = document.querySelector('.date-card.active');
	        if (activeCard) {
	            activeCard.classList.remove('active');
	        }
	        dateCard.classList.add('active');
	        currentIndex = clickedIndex;
	        updateVisibleCards(data, currentIndex)
	    });
	});
	function updateVisibleCards(data, currentIndex) {
	    dateBar.style.transform = `translateX(-${currentIndex * (100 / 3)}%)`;
	    const activeCard = document.querySelector('.date-card.active');
	    if (activeCard) {
	        activeCard.classList.remove('active');
	    }
	    dateCards[currentIndex].classList.add('active');
	
	    // Disable or enable buttons based on currentIndex
	    if (currentIndex === 0) {
	        prevBtn.classList.add('btn-disabled');
	    } else {
	        prevBtn.classList.remove('btn-disabled');
	    }
	    if (currentIndex === dateCards.length - 1) {
	        nextBtn.classList.add('btn-disabled');
	    } else {
	        nextBtn.classList.remove('btn-disabled');
	    }
	    createTime(data, currentIndex);
	    pickTime();
       	confirmInfo();
	}
}
function pickTime(){
	const slots = document.querySelectorAll('.slot-card');
	slots.forEach(slot => {
		slot.addEventListener('click', function(){
			const pickedslot = document.querySelector('.slot-card.picked');
			if(pickedslot){
				pickedslot.classList.remove('picked');
			}
			slot.classList.add('picked');
		})
	});
}
//function to confirm/cancel
function confirmInfo(){
	const confirm_btn = document.getElementById("next");
	const cancel_btn = document.getElementById("cancel");
	const typeid = document.querySelector('.booking-contain-type').id;
	confirm_btn.addEventListener("click", function() {
	    const date = document.querySelector('.date-card.active');
	    const slot = document.querySelector('.slot-card.picked');
	    if (slot) {
	        const timeId = slot.querySelector('.slot-time').id;
	        fetch('kiemtranguoidung')
	            .then(response => response.json())
	            .then(result => {
					//console.log(result);
	                // Kiểm tra kết quả từ server
	                if (result == "1") {
						fetch(`trunglichhen?&date=${date.id}&time=${timeId}`, {
					        method: 'GET',
					        headers: {"Content-type": "application/json; charset=UTF-8"}
					    })
					    .then(response => response.json())
					    .then(data => {
					        console.log(data);
					        if(data=='1'){
								alert("ban da co lich hen vao thoi gian nay, vui long chon lich hen khac");
							}else {
								const url = `xacnhan.jsp?doctorId=${doctorId}&service=${typeid}&date=${date.id}&time=${timeId}`;
	                    		window.location.href = url;
							}
					    })
					    .catch(error => {
					        console.error('Lỗi:', error);
					    });
	                } else if (result == "0") {
	                    console.log('Không có người dùng trong session');
	                    //yeu cau dang nhap
	                    handleLoginLinkClick();
	                } else {
	                    console.error('Kết quả không hợp lệ từ server');
	                }
	            })
	            .catch(error => {
	                console.error('Lỗi:', error); // Xử lý lỗi nếu có
	            });
	    } else {
	        console.log('Chưa chọn slot');
	    }
	});

	cancel_btn.addEventListener("click",function(){
		window.location.href = 'index.jsp';
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
