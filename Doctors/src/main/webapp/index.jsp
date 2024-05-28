<%@page import="model.BacSy"%>
<%@page import="model.NguoiDung"%>
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
                    <nav id="myHeader" class="navbar-main">
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
                            	Object ob1 = session.getAttribute("bacsy");
                            	BacSy bacsy = null;
								NguoiDung nguoidung = null;
								if(ob!=null){
									nguoidung = (NguoiDung)ob;
								}
								
								if(ob1 != null){
									bacsy = (BacSy)ob1;
								}
								
								if(nguoidung != null){
							%>
								<li id="<%=nguoidung.getId()%>" class="navbar-item uslide user ">
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
							<%} else if(bacsy != null) { %>
								<li id="<%=bacsy.getId()%>" class="navbar-item uslide user ">
	                                <img alt="bacsy" src="<%=bacsy.getHinhanh()%>" style="width:30px;">
	                                <%=bacsy.getTen().toUpperCase() %>
	                                <div class="user navbar__link">
	                                  <div class="dropdown">
	                                    <ul class="dropdown-list">
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-circle-info"></i>
	                                            <a href="bacsy_lichhen.jsp" class="dropdown-item__link">Thông tin tài khoản</a>
	                                        </li>
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-lock"></i>
	                                            <a href="bacsy_doimatkhau.jsp" class="dropdown-item__link">Đổi mật khẩu</a>
	                                        </li>
	                                        <li class="dropdown-item">
	                                            <i class="fa-solid fa-clock"></i>
	                                            <a href="dangxuat" id="log-out" class="dropdown-item__link">Đăng xuất</a>
	                                        </li>
	                                    </ul>
	                                  </div>
	                                </div>
	                              </li>
							<%}else{ %>
							    <li class="navbar-item navbar-item--separate">
							        <a href="dangky.jsp" class="navbar__link uslide">ĐĂNG KÝ</a>
							    </li>
							    <li class="navbar-item">
							        <span id="loginLink" class="navbar__link uslide navbar-item--login">ĐĂNG NHẬP</span>
							    </li>
							<%}%>
							</ul>
                        </div>
                    </nav>
                </div>
                
                <nav class="navbar-contact">
                    <div class="navbar-contact-list">
                        <span class="navbar-contact-item">Bác Sỹ</span>
                        <span class="navbar-contact-item navbar-contact-item_slash"> / </span>
                        <span class="navbar-contact-item">Liên Hệ Chúng Tôi</span>
                    </div>
                </nav>
            </div>
        </header>

        <div class="container">
            <div class="grid">
                <!-- Search -->
                <div class="search-wrap">
                    <div class="search__background">
                        <span class="search__background-icon1">+</span>
                        <span class="search__background-icon2">+</span>
                    </div>
                    <div class="container-search">
                        <div class="search-title">
                            <span class="search-title__text">TÌM KIẾM BÁC SỸ</span>
                        </div>
                        <ul class="search-list">
                            <li class="search-item">
                                <div class="search-filter__title" >Tỉnh/ Thành</div>
                                <select name="area" id="area" class="search-filter__input" >
                                    <option value="" selected disabled>Chọn tỉnh/ thành</option>
                                </select>
                            </li>
                            <li class="search-item">
                                <div class="search-filter__title" >Bệnh Viện</div>
                                <select name="hospital" id="hospital" class="search-filter__input" >
                                    <option value="" selected disabled>Chọn bệnh viện</option>
                                </select>
                            </li>
                            <li class="search-item">
                                <div class="search-filter__title" >Chuyên Khoa</div>
                                <select name="specialty" id="specialty" class="search-filter__input" >
                                    <option value="" selected disabled>Chọn chuyên khoa</option>
                                </select>
                            </li>
                            <li class="search-item">
                                <div class="search-filter__title">Tên bác sỹ</div>
                                <input type="text" name="doctorname" id="doctorname" style="color:#333;" class="search-filter__input search-filter__area-value" placeholder="Nhập tên bác sỹ">
                            </li>
                        </ul>
                        <div class="search__submit">
                            <button id="btn_search" class="btn__search">Tìm Kiếm</button>
                        </div>
                    </div>
                </div>
                
                <!-- Main site content -->
                <div class="main-site">
                    <div class="grid__row">
                        <!-- Doctor site -->
                        <div class="grid__column-9">
                            <div class="main-heading">
                                <h1 class="main-heading__title">Bác Sỹ</h1>
                                <h2 class="main-heading__description">"Tìm một trong những Bác sỹ xuất sắc nhất để nhận được lời khuyên y tế nhanh chóng và tư vấn chuyên môn cho các vấn đề sức khỏe của bạn. 
                                    Đặt lịch để khám trực tiếp hoặc tư vấn trực tuyến với bác sỹ. Bắt đầu ngay bây giờ!"</h2>
                            </div>
                            <ul class="doctor-list">
                                
                            </ul>
                        </div>
                        <!-- Site news reading -->
                        <div class="grid__column-3">
                            <div class="reading-header">
                                <img src="https://static-cmsads.admicro.vn/cmsads/2023/10/1.pn-1698368491272.png" alt="" class="reading__notice">
                                <img src="https://static-cmsads.admicro.vn/cmsads/2023/09/1.pn-1695367640041.png" alt="" class="reading__notice">
                                <img src="https://static-cmsads.admicro.vn/cmsads/2023/09/2.pn-1695367769467.png" alt="" class="reading__notice">
                            </div>
                            <div class="reading-frame">
                                <h1 class="reading-heading">
                                    <i class="fa-regular fa-newspaper reading-heading-icon"></i>
                                    TIN TỨC SỨC KHỎE Y TẾ
                                </h1>
                            </div>
                            <div class="reading-list-wrap">
                                <ul class="reading-list">
                                    <li class="reading-item">
                                        <div class="reading-item-wrap">
                                            <div class="reading-item__head">
                                                <a href="" class="reading-item-link">
                                                    <img src="https://media.post.rvohealth.io/wp-content/uploads/sites/3/2024/04/lab-blood-vials-732x549-thumbnail.jpg" alt="" class="reading-item-img">
                                                </a>
                                            </div>
                                            <div class="reading-item__body">
                                                <a href="" class="reading-item-link">
                                                    <h2 class="reading-item-title">Migraine linked to increased risk of IBD in new study</h2>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="reading-item">
                                        <div class="reading-item-wrap">
                                            <div class="reading-item__head">
                                                <a href="" class="reading-item-link">
                                                    <img src="https://media.post.rvohealth.io/wp-content/uploads/sites/3/2024/04/Blood-pressure-gauges-732x549-thumbnail.jpg" alt="" class="reading-item-img">
                                                </a>
                                            </div>
                                            <div class="reading-item__body">
                                                <a href="" class="reading-item-link">
                                                    <h2 class="reading-item-title">Could swapping beef for fish like sardines help prevent early death?</h2>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="reading-item">
                                        <div class="reading-item-wrap">
                                            <div class="reading-item__head">
                                                <a href="" class="reading-item-link">
                                                    <img src="https://media.post.rvohealth.io/wp-content/uploads/sites/3/2024/04/sardines-bread-window-732x549-thumbnail.jpg" alt="" class="reading-item-img">
                                                </a>
                                            </div>
                                            <div class="reading-item__body">
                                                <a href="" class="reading-item-link">
                                                    <h2 class="reading-item-title">High blood sugar, blood fats linked to higher anxiety and depression risk</h2>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Page Navigation -->
                <div class="page-navigation-wrap">
                    <div class="grid__row">
                        <div class="grid__column-9">
                            <div class="page-navigation">
                                <span class="btn__page-navigation btn__page-current">1</span>
                                <span class="btn__page-navigation">2</span>
                                <span class="btn__page-navigation">3</span>
                                <span class="btn__page-navigation">></span>
                            </div>
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
	<script src="assets/js/main.js"></script>
    <script src="assets/js/login.js"></script>
    <script src="assets/js/search.js"></script>
    <script src="assets/js/pagination.js"></script>
    
</body>
</html>