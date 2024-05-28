<%@page import="model.NguoiDung"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DoctorInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
    <link rel="stylesheet" href="assets/css/confirm.css">
    <link rel="stylesheet" href="assets/css/successbooking.css">
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
                                    <a href="index.jsp" class="navbar__link uslide">ĐẶT LỊCH</a>
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
	                                <%=nguoidung.getTen().toUpperCase() %>
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
        
        <div class="background-wrap">
        	<img class="background" src="assets/img/xacnhan-bg.jpeg" alt="back-ground"  style="opacity: 0.6">
        	<div class="confirm-site">
            <div class="booking-confirm">
                <div class="booking-info">
                    <div class="booking-header">
                        <i class="fa-solid fa-stethoscope booking-icon"></i>
                        <span class="booking-title">ĐẶT LỊCH KHÁM</span>
                    </div>
                    <div class="booking-info-item">
                        <div class="booking-time">
                            <div class="booking-date">
                                <i class="fa-solid fa-calendar-week booking-date-icon"></i>
                                <span class="booking-at-time date-info"></span>
                            </div>
                            <div class="booking-at">
                                <i class="fa-regular fa-clock booking-at-icon"></i>
                                <span class="booking-at-time time-info">07:00 Sáng</span>
                            </div>
                        </div>
                        <div class="booking-change">
                            <button class="booking-change-text">Thay đổi lịch hẹn</button>
                            <i class="fa-solid fa-arrow-pointer" style="color:#696161; font-size: 1.4rem;"></i>
                        </div>
                    </div>
                    <div class="booking-info-item">
                        <div class="booking-contain-item">
                            <div class="booking-doctor-img">
                                <img src="https://img.icliniq.com/doc_photo/dr-shardendu-tomar-93352-619fca7b24799.jpg" alt="Doctor photo" class="doctor-item__image">
                            </div>
                            <p class="booking-doctor-name"></p>
                        </div>
                        <div class="booking-contain-item">
                            <i class="fa-solid fa-notes-medical booking-others"></i>
                            <div class="booking-others" id="speciality-label">Chuyên Khoa: </div>
                            <div class="booking-others" id="speciality-name"></div>
                        </div>
                        <div class="booking-contain-item">
                            <i class="fa-regular fa-hospital booking-others"></i>
                            <div class="booking-others" id="booking-hospital"></div>
                        </div>
                        <div class="booking-contain-item">
                            <i class="fa-solid fa-money-check-dollar booking-others"></i>
                            <div class="booking-others" id="booking-fee-lable">Phí đặt lịch: </div>
                            <div class="booking-others" id="booking-fee"></div>
                        </div>
                    </div>
                </div>
                <div class="booking-pay">
                    <div class="pay-method">
                        <img class="pay-method-img" src="assets/img/thanh-toan-dien-tu-.jpg" alt="Payment Method">
                    </div>
                    <div class="pay-confirm">
                        <button id="booking-cancel" class="btn-confirm">Hủy Bỏ</button>
                        <button id="booking-pay" class="btn-confirm">Thanh Toán</button>
                    </div>
                </div>
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
    <script src="assets/js/booking.js"></script>
</body>
</html>