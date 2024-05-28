<%@page import="model.NguoiDung"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DoctorInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
   		pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctors</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/main.css">
    <link rel="stylesheet" href="assets/css/booking.css">
    <link rel="stylesheet" href="assets/css/authenForm.css">
    <link rel="stylesheet" href="assets/css/dangky.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.5.2-web/fontawesome-free-6.5.2-web/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	
	<script src="assets/js/formValidation.js"></script>
    
</head>
<body>
	
    <div class="app">
        <header class="header">
            <div class="grid">
                <div class="navbar-main-wrap">
                    <nav id="Header" class="navbar-main">
                        <div class="navbar-main__list">
                            <ul class="navbar-list">
                                <div class="header__logo">
                                    <img src="https://dynamic.design.com/asset/logo/53e9d92f-933e-4380-ad53-d1e91a94b16f/logo-search-grid-1x?logoTemplateVersion=1&v=637907166083400000&text=T%c3%acm+ki%e1%ba%bfm+b%c3%a1c+s%e1%bb%b9&colorpalette=blue" alt="Tìm kiếm Bác Sỹ" class = "header__logo-img">
                                </div>
                                <li class="navbar-item">
                                    <a href="" class="navbar__link uslide">THẢO LUẬN</a>
                                </li>
                                <li class="navbar-item">
                                    <a href="" class="navbar__link uslide">TÌM KIẾM
                                        <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
                                    </a>
                                </li>
                                <li class="navbar-item">
                                    <a href="" class="navbar__link uslide">ĐẶT LỊCH</a>
                                </li>
                                <li class="navbar-item uslide navbar-item__news">
                                    TIN TỨC
                                    <div class="news-list-wrap">
                                        <ul class="news-list">
                                            <li class="news-item">
                                                <a href="" class="news-item__link">Sức Khỏe</a>
                                            </li>
                                            <li class="news-item">
                                                <a href="" class="news-item__link">Khoa Học</a>
                                            </li>
                                            <li class="news-item">
                                                <a href="" class="news-item__link">Bệnh Theo Mùa</a>
                                                <i class="fa-solid fa-fire news-item__icon"></i>
                                            </li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                            <ul class="navbar-list">
							<%
								Object ob = session.getAttribute("nguoiDung");
								NguoiDung nguoidung = null;
								if(ob!=null){
									nguoidung = (NguoiDung)ob;
								}
								if(nguoidung == null){
							%>
							    <li class="navbar-item navbar-item--separate">
							        <a href="dangky.jsp" class="navbar__link uslide">ĐĂNG KÝ</a>
							    </li>
							    <li class="navbar-item">
							        <span id="loginLink" class="navbar__link uslide navbar-item--login">ĐĂNG NHẬP</span>
							    </li>
							<%}else{ %>
								<li id="<%=nguoidung.getId()%>" class="navbar-item uslide user">
	                                <i class="fa-solid fa-user-check"></i>
	                                <%=nguoidung.getTen().toUpperCase()%>
	                                <div class="user navbar__link">
	                                  <div class="dropdown">
	                                    <ul class="dropdown-list">
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-circle-info"></i>
	                                            <a href="nguoidung_lichhen.jsp" class="dropdown-item__link">Thông tin tài khoản</a>
	                                        </li>
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-lock"></i>
	                                            <a href="nguoidung_doimatkhau.jsp" class="dropdown-item__link">Đổi mật khẩu</a>
	                                        </li>
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-clock"></i>
	                                            <a href="dangxuat" id="log-out" class="dropdown-item__link">Đăng xuất</a>
	                                        </li>
	                                    </ul>
	                                  </div>
	                                </div>
	                              </li>
							<%} %>
							</ul>
                        </div>
                    </nav>
                </div>
                
            </div>
        </header>

        <div class="container result-background">
            <div class="grid">
                <!-- Main site content -->
                <div class="doctor-site">
                    <!-- Doctor site -->
                            <div class="doctor-item--result">
                                <div class="doctor-wrap">
                                    <a href="" class="doctor-item-link">
                                        <img src="" alt="Doctor photo" class="doctor-item__image">
                                    </a>
                                    <div class="doctor-item__sumary">
                                        <div class="doctor__name">
                                            <span class="doctor-link"></span>
                                        </div>
                                        <div class="doctor-item__unit">
                                            <p class="doctor-item-label">
                                                Đơn vị: 
                                                <a href="" id="unit" class = "doctor-item-name doctor-item-name-link"></a>
                                            </p>
                                            
                                        </div>
                                        <div class="doctor-item__experience">
                                            <p class="doctor-item-label">
                                                Kinh nghiệm:
                                                <span id="kinhnghiem" class="doctor-item-name"></span>
                                            </p>
                                        </div>
                                        <ul class="doctor-item__specialty">
                                            <li class="specialty__name">
                                                <a href="" class="specialty__name-link"></a>
                                            </li>
                                        </ul>
                                        <div class="doctor-item__rating" style="margin: 30px 0;">
                                            <div class="rating-star">
                                                <i class="fa-solid fa-star star-checked"></i>
                                                <i class="fa-solid fa-star star-checked"></i>
                                                <i class="fa-solid fa-star star-checked"></i>
                                                <i class="fa-solid fa-star star-checked"></i>
                                                <i class="fa-solid fa-star"></i>
                                            </div>
                                            <span class="rating-info"></span>
                                        </div>
                                        <div class="booking-contain-item">
				                            <i class="fa-solid fa-money-check-dollar booking-others"></i>
				                            <div class="booking-others" id="booking-fee-lable">Phí đặt lịch: </div>
				                            <div class="booking-others" id="booking-fee"></div>
				                        </div>
                                        <div class="booking-contain-type">
				                            <input type="radio" class="type-radi-btn" id="offline" name="booking-type" value="offline" >
				                            <label for="offline" class="type-radi-btn" >Khám trực tiếp</label><br>
				                            <input type="radio" class="type-radi-btn" id="online" name="booking-type" value="online" >
				                            <label for="online" class="type-radi-btn" >Tư vấn trực tuyến</label><br>
				                        </div>
                                        <div class="doctor-booking-btn">
                                            <button id="cancel" class="btn__doctor-contact btn__doctor-book">
                                                Hủy bỏ
                                            </button>
                                            <button id="next" class="btn__doctor-contact btn__doctor-consult">
                                                <i class="fa-regular fa-circle-check"></i>
                                                Tiếp theo
                                            </button>
                                            
                                        </div>
                                    </div>
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
                                        <ul class="slot-group">
                                            <li class="slot-group--item">
                                                <div class="slot-lable">
                                                    <i class="fa-regular fa-sun slot-icon"></i>
                                                    <p class="slot-name">Buổi sáng</p>
                                                </div>
                                                <div id="morning" class="slot-cards">
                                         
                                                </div>
                                            </li>
                                            <div class="separator" style="background: #ccccdd; height: 0.3px; width: 93%;"></div>
                                            <li class="slot-group--item">
                                                <div class="slot-lable">
                                                    <i class="fa-solid fa-cloud-sun slot-icon"></i>
                                                    <p class="slot-name">Buổi chiều</p>
                                                </div>
                                                <div id="afternoon" class="slot-cards">
                                                    
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="doctor-separator"style="background: #ccccdd; height: 0.3px; width: 90%;"></div>
                      
                </div>
            </div>
        </div>

        <footer class="footer">
            <div class="grid">
                <nav class="footer-wrap">
                    <div class="footer__header">
                        <div class="header__logo">
                            <img src="https://dynamic.design.com/asset/logo/53e9d92f-933e-4380-ad53-d1e91a94b16f/logo-search-grid-1x?logoTemplateVersion=1&v=637907166083400000&text=T%c3%acm+ki%e1%ba%bfm+b%c3%a1c+s%e1%bb%b9&colorpalette=blue" alt="Tìm kiếm Bác Sỹ" class = "header__logo-img">
                        </div>
                        <div class="footer__msg">
                            <div class="footer__msg-img-wrap">
                                <i class="fa-solid fa-user-nurse footer__msg-img"></i>
                            </div>
                            <p class="footer__msg-text">HỖ TRỢ 24/7</p>
                        </div>
                    </div>
                    <div class="footer__content">
                        <ul class="footer__content-list">
                            <li class="footer__content-item">Book an appointment</li>
                            <li class="footer__content-item">Chat with a Doctor</li>
                            <li class="footer__content-item">Physician Directory</li>
                            <li class="footer__content-item">Online COVID-19 Care</li>
                        </ul>
                        <ul class="footer__content-list">
                            <li class="footer__content-item">Terms</li>
                            <li class="footer__content-item">Privacy</li>
                            <li class="footer__content-item">About Us</li>
                            <li class="footer__content-item">Our Policy</li>
                        </ul>
                        <ul class="footer__content-list">
                            <li class="footer__content-item">Tools</li>
                            <li class="footer__content-item">Medical Review Team</li>
                            <li class="footer__content-item">Deals & Offers</li>
                            <li class="footer__content-item">Support</li>
                        </ul>
                    </div>
                    <div class="footer__bottom">
                        <p class="footer__bottom-msg">This website uses cookies to ensure you get the best experience on our website. </p>
                    </div>
                </nav>
            </div>
        </footer>

    </div>
    
	<script src="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.js" integrity="sha512-NhSC1YmyruXifcj/KFRWoC561YpHpc5Jtzgvbuzx5VozKpWvQ+4nXhPdFgmx8xqexRcpAglTj9sIBWINXa8x5w==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
        var header = document.getElementById("Header");
        header.classList.add("scrolled");
        var confirm = document.querySelector('.confirm-site');
		confirm.style.display = 'block';
    </script>
    <script src="assets/js/login.js"></script>
    <script src="assets/js/bookingAppointment.js"></script>
</body>
</html>