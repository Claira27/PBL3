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
    <link rel="stylesheet" href="assets/css/event.css">
    <link rel="stylesheet" href="assets/css/authenForm.css">
    <link rel="stylesheet" href="assets/css/dangky.css">
    <link rel="stylesheet" href="assets/css/doimatkhau.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.5.2-web/fontawesome-free-6.5.2-web/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	
	<script src="assets/js/formValidation.js"></script>
    
</head>
<body>
	<%
		Object ob = session.getAttribute("bacsy");
		BacSy bacsy = null;
		if(ob!=null){
			bacsy = (BacSy)ob;
		}
		if(bacsy == null){
	%>
	<a href="index.jsp" class="navbar__link uslide">Bạn chưa đăng nhập, quay lại trang chủ
        <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
    </a>
    <%}else{ %>
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
                                    <a href="index.jsp" class="navbar__link uslide">TÌM KIẾM
                                        <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
                                    </a>
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
                              <li id="<%=bacsy.getId()%>" class="navbar-item user" style="cursor:default;">
	                                <img alt="avata" src="<%=bacsy.getHinhanh()%>" style="width:30px; margin-right:5px;">
	                                <%=bacsy.getTen().toUpperCase() %>
	                          </li>
                            </ul>
                        </div>
                    </nav>
                </div>
                
            </div>
        </header>
        <%
		String baoloi = (request.getAttribute("error")+"").equals("null")?"":request.getAttribute("error")+"*";
		
		%>
        <div class="grid">
            <div class="change-info">
                <!-- Sign-in form -->
                <form action="doimatkhaubacsy" id="form-5" method="POST">
                    <div class="dang-ky-wrap">
                        <div class="dang-ky">
                            <div class="auth-form__container">
                                <div class="auth-form__header">
                                    <h3 class="change-title">ĐẶT LẠI MẬT KHẨU</h3>
                                </div>
                                <span class="form-message invalid" style="color: red;" id = "baoloi"><%=baoloi%></span>
                                <div class="auth-form__info">
                                    <div class="auth-form__form auth-form__form--dangky">
                                        <div class="auth-form__group">
                                            <label for="matkhau-cu" class="form-label">Mật khẩu cũ</label>
                                            <input type="password" class="auth-form__input" name="matkhau-cu" id="matkhau-cu">
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="auth-form__group">
                                            <label for="matkhau-dangky" class="form-label">Mật khẩu mới </label>
                                            <input type="password" class="auth-form__input" name="matkhau-dangky" id="matkhau-dangky">
                                            <span class="form-message"></span>
                                        </div>
                                        <div class="auth-form__group">
                                            <label for="matkhau-confirm-dangky" class="form-label">Nhập lại mật khẩu</label>
                                            <input type="password" class="auth-form__input" name="matkhau-confirm-dangky" id="matkhau-confirm-dangky">
                                            <span class="form-message"></span>
                                        </div>
                                    </div>
                                    <div class="auth-form__control">
                                        <span id="btn-back" class="btn btn-normal btn-signin">TRỞ LẠI</span>
                                        <button class="btn btn--primary btn form-submit btn-signin">ĐẶT LẠI</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div> 
            <div class="menu-wrap">
                <h2 class="menu-title">TÀI KHOẢN CỦA BẠN</h2>
                <div class="menu-list">
	                  <a href="bacsy_lichhen.jsp" class="menu-item">Lịch Hẹn</a>
	                  <a href="bacsy_dangkylich.jsp" class="menu-item">Đăng Ký Lịch</a>
                      <a href="bacsy_xemthongke.jsp" class="menu-item">Xem Thống kê</a>
	                  <span class="menu-item on-task">Đặt Lại Mật Khẩu</span>
	                  <a href="index.jsp" class="menu-item">Trang Chủ</a>
	                  <a href="dangxuat" class="menu-item">Đăng Xuất</a>
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
    </script>
    <script>
        //output
        Validator({
            form: '#form-5',
            formGroupSelector: '.auth-form__group',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#matkhau-cu', 'Vui lòng nhập mật khẩu cũ'),
                Validator.isRequired('#matkhau-dangky', 'Mật khẩu mới không được để trống'),
                Validator.isRequired('#matkhau-confirm-dangky', 'Mật khẩu mới không được để trống'),
                Validator.minLength('#matkhau-cu',6),
                Validator.minLength('#matkhau-dangky',6),
                Validator.isConfirmed('#matkhau-confirm-dangky',function(){
                  return document.querySelector('#form-5 #matkhau-dangky').value;
                }, 'Mật khẩu nhập lại không chính xác'),
            ],
            /*
            onSubmit: function(data){
            //  Call API
              console.log(data);
            },*/
        });
      </script>
      <script>
      	 const back = document.getElementById('btn-back');
      	back.addEventListener('click', function() {
      	    window.history.back();
      	});
      </script>
    <%} %>
</body>
</html>