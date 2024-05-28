function fetchData(){
    fetch('taiadminthongke')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        renderStatistics(data);
        renderUsers(data);
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();
function renderStatistics(data){
	const nguoidung = document.getElementById('g1-nguoidung');
	const bacsy = document.getElementById('g1-bacsy');
	const timkiem = document.getElementById('g1-timkiem');
	const datlich = document.getElementById('g1-datlich');
	const tuvan = document.getElementById('g1-tuvan');
	
	nguoidung.innerHTML = `${data.soluongNd}`;
	bacsy.innerHTML = `${data.soluongBs}`;
	timkiem.innerHTML = `${data.timkiem}`;
	datlich.innerHTML = `${data.datlich}`;
	tuvan.innerHTML = `${data.tuvan}`;
	
}
function renderUsers(data){
	const nguoidungwrap = document.getElementById('g3-nguoidung');
	nguoidungwrap.innerHTML = '';
	const nguoidungHtml = data.nguoidung.map(item => {
		return `
			<div class="g3-item">
	            <div class="g3-logo c1">U</div>
	            <p class="g3-name c2">${item.ten}</p>
	            <p class="g3-date c3">${item.ngaySinh}</p>
	            <p class="g3-place c4">${item.diaChi}</p>
	            <p class="g3-num c5">${item.tong}</p>
	        </div>
		`;
	}).join('');
	nguoidungwrap.innerHTML += nguoidungHtml;
	
	const bacsywrap = document.getElementById('g3-bacsy');
	bacsywrap.innerHTML = '';
	const bacsyHtml = data.bacsy.map(item => {
		return `
			<div class="g3-item">
                <img src="${item.hinhanh}" alt="doctor-photo" class="g3-img c6">
                <p class="g3-name c7">${item.ten}</p>
                <p class="g3-place c8">${item.benhvien}</p>
                <p class="g3-num c9">${item.tong}</p>
            </div>
		`;
	}).join('');
	bacsywrap.innerHTML += bacsyHtml;
}