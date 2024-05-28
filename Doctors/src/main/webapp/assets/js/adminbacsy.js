function fetchData(){
    fetch('taiadminthongke')
    .then(response => response.json())
    .then(data => {
		console.log(data);
        renderUsers(data.bacsy);
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();
function renderUsers(data){
	const bacsywrap = document.querySelector('.g3-body');
	bacsywrap.innerHTML = '';
	const bacsyHtml = data.map(item => {
		return `
			<div id='${item.id}' class="g3-item">
                <img src="${item.hinhanh}" alt="doctor-photo" class="g3-img s1">
                <p class="g3-name s2">${item.ten}</p>
                <p class="g3-place s3">${item.benhvien}</p>
                <p class="g3-num s4">${item.tong}</p>
                <div class="rating-star s5">
                	${'<i class="fa-solid fa-star start star-checked"></i>'.repeat(item.danhGia)}
                    ${'<i class="fa-solid fa-star start"></i>'.repeat(5 - item.danhGia)}
                </div>
                <a href="admin2_update.jsp?id=${item.id}" class="g3-change s6">
                    <i class="fa-solid fa-pen-to-square change-icon"></i>
                </a>
            </div>
		`;
	}).join('');
	bacsywrap.innerHTML += bacsyHtml;
}
//search handle
const input = document.querySelector('.search-key');
    
input.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
       fetch(`admintimkiembacsy?ten=${input.value}`)
	    .then(response => response.json())
	    .then(data => {
			console.log(data);
			changeTitle();
	        renderUsers(data);
	    })
	    .catch(error => {
	        console.error('Lỗi:', error);
	    }); 
    }
});

function changeTitle(){
	const title = document.querySelector('.g3-title');
	title.innerHTML = 'Thông Tin Bác Sỹ';
}

