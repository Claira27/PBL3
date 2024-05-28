var ischange = 0;
var tem = [
    [], // tem[0] corresponds to data of id 2
    [], // tem[1] corresponds to data of id 3
    [], // tem[2] corresponds to data of id 4
    [], // tem[3] corresponds to data of id 5
    [], // tem[4] corresponds to data of id 6
    [], // tem[5] corresponds to data of id 7
    []  // tem[6] corresponds to data of id 8
];

function fetchData(){
    fetch('tailichdangky')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        //gan cho bien tem
        data.dangkylich.forEach(date => {
		    tem[date.ngay - 2].push(date.id_time);
		});
		console.log(tem);
        renderTimeData(data.thu, 2);
        createslot(data, 2);
        pickTimeHandler(data);
        pickDateAndTime(2);
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();

function renderTimeData(data, id) {
    let htmlDate = document.querySelector('.daysofweek');
    htmlDate.innerHTML = '';
    
    let htmlcontent = data.map(date => {
        if (date.id == id) {
            return `
                <div class="dayofweek dayofweek-pick" id="${date.id}">
                    <p class="daytext">${date.time}</p>
                    <div class="sum">
                        <i class="fa-solid fa-circle sum-icon"></i>
                        3 suất
                    </div>
                </div>
            `;
        } else {
            return `
                <div class="dayofweek" id="${date.id}">
                    <p class="daytext">${date.time}</p>
                    <div class="sum">
                        <i class="fa-solid fa-circle sum-icon"></i>
                        3 suất
                    </div>
                </div>
            `;
        }
    }).join('');
    
    htmlDate.innerHTML = htmlcontent;   
}

function createslot(data, dateid) {
    let htmlDate = document.querySelector('.pick-group');
    htmlDate.innerHTML = '';

    let htmlcontent = data.slot.map(timeslot => {
        // Check if there's a matching date and time slot
        let isPicked = tem[dateid - 2].includes(timeslot.id);
        
        if (isPicked) {
            return `
                <div class="picktime time-picked" id="${timeslot.id}">${timeslot.time}</div>
            `;
        } else {
            return `
                <div class="picktime" id="${timeslot.id}">${timeslot.time}</div>
            `;
        }
    }).join('');

    htmlDate.innerHTML = htmlcontent;
}

function pickDateAndTime(date_id) {
    const slots = document.querySelectorAll('.picktime');
    slots.forEach(slot => {
        slot.addEventListener('click', function(){
            slot.classList.toggle('time-picked');
            let timeslotId = parseInt(slot.id);
            if (tem[date_id - 2].includes(timeslotId)) {
                // Remove the timeslot from tem if already present
                tem[date_id - 2] = tem[date_id - 2].filter(id => id !== timeslotId);
            } else {
                // Add the timeslot to tem if not present
                tem[date_id - 2].push(timeslotId);
            }
            ischange = 1;
            console.log(ischange);
        });
    });
}

function pickTimeHandler(data){
    const slots = document.querySelectorAll('.dayofweek');
    slots.forEach(slot => {
        slot.addEventListener('click', function(){
            slots.forEach(s => s.classList.remove('dayofweek-pick'));
            // Add 'dayofweek-pick' class to the clicked slot
            slot.classList.add('dayofweek-pick');
            let date_id = slot.id;
            createslot(data, date_id);
            pickDateAndTime(date_id);
        });
    });
}

function handleBeforeUnload(event) {
    // Check if any changes have been made (ischange == 1)
    if (ischange == 1) {
        // Display a confirmation message
        const save = confirm("Bạn có chắc muốn lưu thay đổi?");
        if (save) {
            // Make a fetch request to save the data
            fetch('dangkylich', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ tem: tem }), // Corrected the JSON structure
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    }
}

// Add event listener for beforeunload event
window.addEventListener("beforeunload", handleBeforeUnload);
