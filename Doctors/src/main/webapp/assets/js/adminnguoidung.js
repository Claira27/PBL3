function fetchData(){
    fetch('taiadminthongke')
    .then(response => response.json())
    .then(data => {
		console.log(data);
        renderUsers(data.nguoidung);
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();
function renderUsers(data){
	const nguoidungwrap = document.querySelector('.g3-body');
	nguoidungwrap.innerHTML = '';
	const nguoidungHtml = data.map(item => {
		return `
			<div id='${item.id}' class="g3-item">
                <div class="g3-logo s1">U</div>
                <p class="g3-name s2">${item.ten}</p>
                <p class="g3-date s3">${item.ngaySinh}</p>
                <p class="g3-place s4">${item.diaChi}</p>
                <p class="g3-num s5">${item.tong}</p>
                <a href="admin3_update.jsp?id=${item.id}" class="g3-change s6">
                    <i class="fa-solid fa-pen-to-square change-icon"></i>
                </a>
            </div>
		`;
	}).join('');
	nguoidungwrap.innerHTML += nguoidungHtml;
}
//search handle
const input = document.querySelector('.search-key');
    
input.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
       fetch(`admintimkiemnguoidung?ten=${input.value}`)
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
	title.innerHTML = 'Thông Tin Người Dùng';
}

