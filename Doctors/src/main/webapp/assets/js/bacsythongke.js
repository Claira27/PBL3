// Define the theme colors
window.theme = {
    primary: '#007bff'
};
function fetchData(){
    fetch('taibacsythongke')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        ratingResult(data)
        initializeBar(data)
    })
    .catch(error => {
        console.error('Lỗi:', error);
    });
}
fetchData();
function ratingResult(data){
	const star = document.getElementById('start-result');
	const total = document.getElementById('ratings');
	star.innerHTML = ` ${data.danhgia}/5 `;
	total.innerHTML = ` ${data.tong} `;
}
// Initialize the bar chart
function initializeBar(statisticsdata){
	new Chart(document.getElementById("chartjs-bar"), {
	    type: "bar",
	    data: {
	        labels: ["Th1", "Th2", "Th3", "Th4", "Th5", "Th6", "Th7", "Th8", "Th9", "Th10", "Th11", "Th12"],
	        datasets: [{
	            label: "Đặt Khám",
	            backgroundColor: window.theme.primary,
	            borderColor: window.theme.primary,
	            hoverBackgroundColor: window.theme.primary,
	            hoverBorderColor: window.theme.primary,
	            data: [27, 10, 25, 67, 35, 57, 0, 0, 0, 0, 0, 0],
	            //data: statisticsdata.datlich,
	            barPercentage: 0.75,
	            categoryPercentage: 0.5
	        }, {
	            label: "Tư Vấn",
	            backgroundColor: "#dee2e6",
	            borderColor: "#dee2e6",
	            hoverBackgroundColor: "#dee2e6",
	            hoverBorderColor: "#dee2e6",
	            data: [20, 40, 35, 55, 65, 77, 10, 0, 0, 0, 0, 0, 0],
	            //data: statisticsdata.khamtructuyen,
	            barPercentage: 0.75,
	            categoryPercentage: 0.5
	        }]
	    },
	    options: {
	        scales: {
	            y: {
	                beginAtZero: true
	            },
	            x: {
	                grid: {
	                    color: "transparent"
	                }
	            }
	        }
	    }
	});
}
