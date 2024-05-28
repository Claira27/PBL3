function fetchData(id_date, mes){
	fetch('upcoming')
	.then(response => response.json())
	.then(data => {
		console.log(data);
		createDateSite();
		renderTimeData(data.dateCards, id_date);
		createEvent(data.bookingInfo, id_date);
		pickDateAndTime(data.bookingInfo, mes);
		controlEvents(mes,id_date);
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
}

//render upcoming bookings 
function createDateSite(){
	let htmlDate = document.querySelector('.event-site');
	htmlDate.innerHTML = '';
	
	htmlDate.innerHTML += `
	<div id="schedule" class="schedule-wrap">
	    <div class="slot-header">
	        <button id="prev-btn"class="date-btn">
	            <i class="fa-solid fa-chevron-left arrow-icon"></i>
	        </button>
	        <div class="date-bar" id="date-bar">
	            <div class="date-cards">
	                
	            </div>
	        </div>
	        <button id="next-btn" class="date-btn">
	            <i class="fa-solid fa-chevron-right arrow-icon"></i>
	        </button>
	    </div>
	    
	    <div class="event-list">
	        
	    </div>
	</div>
	`;
}
function renderTimeData(data, id_date){
	let date_cards = document.querySelector(".date-cards");
	date_cards.innerHTML = '';
	for(let i = 0; i < data.length; i++){
		date_cards.innerHTML += createDate(data[i], i, id_date);
	}
}
function createDate(data, i, id_date){
    if(data.id == id_date){
		return `
        <button class="date-card active" id="${i}">
            <p class="date-value">${data.time}</p>
        </button>
    `;
	}else{
		return `
        <button class="date-card" id="${i}">
            <p class="date-value">${data.time}</p>
        </button>
    `;
	}
}
function createEvent(data, id_date){
	let event_list = document.querySelector('.event-list');
	event_list.innerHTML = '';
    let htmlcontent = data
        .filter(event => event.date.id == id_date) // Lọc ra các sự kiện có cùng dateid với id_date
        .map(event => `
        <div class="event-list-wrap">
            <div id="${event.id}" class="event-item">
                <div class="doctor-info">
                    <div class="doctor">
                        <div id="service" class="info">
                            <span class="service-info">Khám tại: ${event.bacsy.unit}</span>
                        </div>
                        <div id="time" class="time-info">
                            <i class="fa-regular fa-clock booking-at-icon"></i>
                            <span class="booking-at-time">${event.time.time}</span>
                        </div>
                    </div>
                    <div class="doctor-separator"style="background: #ccccdd; height: 0.3px; width: 100%; margin:10px 5px 0 10px"></div>
                    <div class="group1">
                        <div class="doctor-name">
                        	<img src="${event.bacsy.imageUrl}" alt="Doctor photo" class="doctor-image">
                        	${event.bacsy.name}
                        </div>
                        <div id="specialty" class="info">
                            <i class="fa-solid fa-stethoscope"></i>
                            Khoa:  ${event.bacsy.specialties[0]}
                        </div>
                    </div>
                </div>
                <div class="edit-btns">
                    <div class="delete btn-edit">
                        Hủy
                        <i class="fa-solid fa-trash"></i>
                    </div>
                </div>
            </div>
        </div>
        `)
        .join('');

    event_list.innerHTML += htmlcontent;
    if(event_list.innerHTML == ''){
		event_list.innerHTML +=`
		<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZiSmTGqOuKtLmOuCd8faSMXnk5nO7EdVsUGflNTLqWA&s" alt="no event" class="no-event-img">
		`;
	}
	controlEvents(0, 0);
}

//pick a time scipt
function pickDateAndTime(data, mes){
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
	    if(mes == 1){
			createOnlineConsult(data, currentIndex);
		}
		else{
			createEvent(data, currentIndex);	
		}
	}
}

fetchData(0, 0);

//tab choices
// Lấy tất cả các phần tử có class là "choice-msg"
var choices = document.querySelectorAll('.choice-msg');

var historyTab = document.getElementById('history');
historyTab.addEventListener('click', function() {
    choices.forEach(function(item) {
        item.classList.remove('tab');
    });
	
    this.classList.add('tab');
    
    fetch('history')
	.then(response => response.json())
	.then(data => {
		console.log(data);
		createHistory(data);
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
});
var notRatingTab = document.getElementById('not-rating');
notRatingTab.addEventListener('click', function() {
    choices.forEach(function(item) {
        item.classList.remove('tab');
    });

    this.classList.add('tab');
    let event_list = document.querySelector('.event-site');
	event_list.innerHTML = '';
	fetch('notrated')
	.then(response => response.json())
	.then(data => {
		console.log(data);
		createHistory(data, 0);
		//rating action
		RatingHandler();
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
});
//rating action script
function RatingHandler(){
	const rating_star = document.querySelectorAll('.rating-star');
	// Add cursor pointer style to each star
    rating_star.forEach(item => {
        item.style.cursor = 'pointer';
        const event_item = item.closest('.event-item');
        const event_id = event_item.id;
        const stars = event_item.querySelectorAll('.rating-star .fa-star');
        stars.forEach((star, index) => {
		    star.addEventListener('mouseover', () => {
		        highlightStars(index, true);
		    });
		
		    star.addEventListener('mouseout', () => {
		        highlightStars(index, false);
		    });
		
		    star.addEventListener('click', () => {
		        selectStars(index);
		    });
		});
		
		function highlightStars(index, isTemporary) {
		    for (let i = 0; i <= index; i++) {
		        if (isTemporary) {
		            stars[i].classList.add('star-checked');
		        } else {
		            stars[i].classList.remove('star-checked');
		        }
		    }
		}
		
		function selectStars(index) {
		    stars.forEach(star => {
		        star.classList.remove('star-checked');
		    });
		    for (let i = 0; i <= index; i++) {
		        stars[i].classList.add('star-checked');
		    }
		    // Call your function here
		    const confirmation = confirm(`Bạn có muốn đánh giá ${index + 1}/5 sao cho dịch vụ khám này?`);
		    if(confirmation){
				const eventhtml = item.closest('.event-list-wrap');
				eventhtml.remove();
				fetch(`danhgia?id=${event_id}&star=${index + 1}`)
	            .then(response => response.json())
	            .then(data => {
					console.log(data);
					alert('Cảm ơn bạn đã đánh giá dịch vụ của chúng tôi!');
				})
				.catch(error => {
					console.log(error);
				})
			}
		    console.log(`${event_id}. Rated ${index + 1} stars`);
		}
    });
}

var upcomingTab = document.getElementById('upcoming');
upcomingTab.addEventListener('click', function() {
    choices.forEach(function(item) {
        item.classList.remove('tab');
    });

    this.classList.add('tab');
    fetch('upcoming')
	.then(response => response.json())
	.then(data => {
		console.log(data);
		createDateSite();
		renderTimeData(data.dateCards, 0);
		createEvent(data.bookingInfo, 0);
		pickDateAndTime(data.bookingInfo, 0);
    	controlEvents(0);
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
});
var upcomingChatTab = document.getElementById('upcoming_chat');
upcomingChatTab.addEventListener('click', function() {
    choices.forEach(function(item) {
        item.classList.remove('tab');
    });
	
    this.classList.add('tab');
    fetch('upcomingservice')
	.then(response => response.json())
	.then(data => {
		console.log(data);
		createDateSite();
		renderTimeData(data.dateCards, 0);
		pickDateAndTime(data.bookingInfo, 1);
	})
	.catch(error => {
        console.error('Lỗi:', error);
    });
});
function createOnlineConsult(data, id_date){
	let event_list = document.querySelector('.event-list');
	event_list.innerHTML = '';
    let htmlcontent = data
        .filter(event => event.date.id == id_date) 
        .map(event => `
        <div class="event-list-wrap">
            <div id="${event.id}" class="event-item">
                <div class="doctor-info">
                    <div class="doctor">
                        <div id="service" class="info">
                            <span class="service-info">Phí dịch vụ: ${event.bacsy.giadichvu}.000 VND</span>
                        </div>
                        <div id="time" class="time-info">
                            <i class="fa-regular fa-clock booking-at-icon"></i>
                            <span class="booking-at-time">${event.time.time}</span>
                        </div>
                    </div>
                    <div class="doctor-separator"style="background: #ccccdd; height: 0.3px; width: 100%; margin:10px 5px 0 10px"></div>
                    <div class="group1">
                        <div class="doctor-name">
                        	<img src="${event.bacsy.imageUrl}" alt="Doctor photo" class="doctor-image">
                        	${event.bacsy.name}
                        </div>
                        <div id="specialty" class="info">
                            <i class="fa-solid fa-stethoscope"></i>
                            Khoa:  ${event.bacsy.specialties[0]}
                        </div>
                    </div>
                </div>
                <div class="edit-btns">
                    <div class="delete btn-edit">
                        <span class="delete-msg">Hủy</span>
                        <i class="fa-solid fa-trash"></i>
                    </div>
                    <div class="change btn-edit">
                        <span class="chat-msg">Trò Chuyện</span>
                        <i class="fa-solid fa-calendar-week"></i>
                    </div>
                </div>
            </div>
        </div>
        `)
        .join('');

    event_list.innerHTML += htmlcontent;
    if(event_list.innerHTML == ''){
		event_list.innerHTML +=`
		<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZiSmTGqOuKtLmOuCd8faSMXnk5nO7EdVsUGflNTLqWA&s" alt="no event" class="no-event-img">
		`;
	}
	controlEvents(1, 0);
}
function createHistory(data){
	let event_list = document.querySelector('.event-site');
	event_list.innerHTML = '';
    let htmlcontent = data
        .map(event => `
        <div class="event-list-wrap" style="width:70%; margin:20px 20px 20px 100px;">
            <div id="${event.id}" class="event-item">
                <div class="doctor-info">
                    <div class="doctor">
                        <div id="service" class="info">
                            <span class="service-info">Phí dịch vụ: ${event.bacsy.giadichvu}.000 VND</span>
                        </div>
                        <div id="time" class="time-info" style="width: 200px;">
                            <i class="fa-regular fa-clock booking-at-icon"></i>
                            <span class="booking-at-time">${event.time.time} ${event.date.time}</span>
                        </div>
                        <div class="rating">
		                    ${event.rating === 0 ? `<span class="rating-info">Chưa đánh giá: </span>
		                    <div class="doctor-item__rating">
		                        <div class="rating-star">
		                            <i id="1" class="fa-solid fa-star"></i>
		                            <i id="2" class="fa-solid fa-star"></i>
		                            <i id="3" class="fa-solid fa-star"></i>
		                            <i id="4" class="fa-solid fa-star"></i>
		                            <i id="5" class="fa-solid fa-star"></i>
		                        </div>
		                    </div>
		                    ` 
		                    : `Đánh giá: 
		                    <div class="doctor-item__rating">
		                        <div class="rating-star">
		                            ${'<i class="fa-solid fa-star star-checked"></i>'.repeat(event.rating)}
		                            ${'<i class="fa-solid fa-star"></i>'.repeat(5 - event.rating)}
		                        </div>
		                    </div>
		                    `}
		                </div>
                    </div>
                    <div class="doctor-separator"style="background: #ccccdd; height: 0.3px; width: 100%; margin:10px 5px 0 10px"></div>
                    <div class="group1">
                        <div class="doctor-name">
                        	<img src="${event.bacsy.imageUrl}" alt="Doctor photo" class="doctor-image">
                        	${event.bacsy.name}
                        </div>
                        <div id="specialty" class="info">
                            <i class="fa-solid fa-stethoscope"></i>
                            Khoa:  ${event.bacsy.specialties[0]}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        `)
        .join('');

    event_list.innerHTML += htmlcontent;
    if(event_list.innerHTML == ''){
		event_list.innerHTML +=`
		<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZiSmTGqOuKtLmOuCd8faSMXnk5nO7EdVsUGflNTLqWA&s" alt="no event" class="no-event-img">
		`;
	}
}
function controlEvents(service, id_date){
	// Adding event listeners to delete and change buttons
	document.querySelectorAll('.delete.btn-edit').forEach(btn => {
	    btn.addEventListener('click', function() {
	        const eventItem = btn.closest('.event-list-wrap');
	        const eventid = btn.closest('.event-item').id;
	        console.log(eventid);
	        const confirmation = confirm('Bạn có chắc chắn muốn hủy sự kiện này?');
	        if (confirmation) {
	            eventItem.remove(); // Remove the event from the DOM
	            fetch(`xoadatlich?id=${eventid}&service=${service}`)
	            .then(response => response.json())
	            .then(data => {
					console.log(data);
					fetchData(0,0);
				})
				.catch(error => {
					console.log(error);
				});
	        }
	    });
	});
	
	
}