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
    <link rel="stylesheet" href="assets/css/dangky.css">
    <link rel="stylesheet" href="assets/css/authenForm.css">
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.5.2-web/fontawesome-free-6.5.2-web/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	
    <script src="assets/js/formValidation.js"></script>
</head>
<body>
<%
	String baoloi = (request.getAttribute("error")+"").equals("null")?"":request.getAttribute("error")+"*";
	String ten = (request.getAttribute("ten")+"").equals("null")?"":request.getAttribute("ten")+"";
	String email = (request.getAttribute("email")+"").equals("null")?"":request.getAttribute("email")+"";
	String diachi = (request.getAttribute("diachi")+"").equals("null")?"":request.getAttribute("diachi")+"";
	String sdt = (request.getAttribute("sdt")+"").equals("null")?"":request.getAttribute("sdt")+"";
	String ngaysinh = (request.getAttribute("ngaysinh")+"").equals("null")?"":request.getAttribute("ngaysinh")+"";
	String gioitinh = (request.getAttribute("gioitinh")+"").equals("null")?"":request.getAttribute("gioitinh")+"";
	
%>
    <div class="app">
        
        
		<div class="dangky-bgd">
        <img class="background-img" src="assets/img/bg1.jpeg" alt="back-ground">
        <div class="content">
		<!-- Sign-in form -->
		<div class="form-wrap">
		<form action="dang-ky" id="form-1" method="POST">
            <div class="dang-ky-wrap">
               <div class="dang-ky">
                   <div class="auth-form__container">
                       <div class="auth-form__header">
                           <h3 class="auth-form__heading">ĐĂNG KÝ TÀI KHOẢN</h3>
                       </div>
                       <span class="form-message invalid" style="color: red;" id = "baoloi"><%=baoloi%></span>
                       <div class="auth-form__info">
                           <div class="auth-form__form auth-form__form--dangky">
                               <div class="auth-form__group">
                               	<label for="ten-dangky" class="form-label">Họ và tên</label>
                                   <input type="text" class="auth-form__input" name="ten-dangky" id="ten-dangky" value="<%=ten%>">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="email-dangky" class="form-label">Email của bạn</label>
                                   <input type="text" class="auth-form__input" name="email-dangky" id="email-dangky" value="<%=email%>">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="matkhau-dangky" class="form-label">Mật khẩu</label>
                                   <input type="password" class="auth-form__input" name="matkhau-dangky" id="matkhau-dangky">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="matkhau-confirm-dangky" class="form-label">Nhập lại mật khẩu</label>
                                   <input type="password" class="auth-form__input" name="matkhau-confirm-dangky" id="matkhau-confirm-dangky">
                                   <span class="form-message"></span>
                               </div>
                           </div>
                           <div class="auth-form__form auth-form__form--dangky">
                               <div class="auth-form__group">
                               	<label for="diachi-dangky" class="form-label">Địa chỉ</label>
                                   <input type="text" class="auth-form__input" name="diachi-dangky" id="diachi-dangky" value="<%=diachi%>">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="sdt-dangky" class="form-label">Số điện thoại</label>
                                   <input type="text" class="auth-form__input" name="sdt-dangky" id="sdt-dangky" value="<%=sdt%>">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="ngaysinh-dangky" class="form-label">Ngày sinh</label>
                                   <input type="Date" class="auth-form__input" name="ngaysinh-dangky" id="ngaysinh-dangky" value="<%=ngaysinh%>">
                                   <span class="form-message"></span>
                               </div>
                               <div class="auth-form__group">
                               	<label for="gioitinh-dangky" class="form-label">Giới tính</label>
								  <div class="gioitinh-wrap">
								      <input type="radio" id="nam" name="gioitinh" value="nam" <%=(gioitinh.equals("nam"))?"checked":""%>>
								      <span class="gioitinh-title">Nam</span>
								      <input type="radio" id="nu" name="gioitinh" value="nu" <%=(gioitinh.equals("nu"))?"checked":""%>>
								      <span class="gioitinh-title">Nữ</span>
								      <input type="radio" id="khac" name="gioitinh" value="khac" <%=(gioitinh.equals("khac"))?"checked":""%>>
								        <span class="gioitinh-title">Khác</span>
								    </div>
								    <span class="form-message"></span>
								</div>
                             </div>
                         </div>
                         <div class="auth-form__aside">
                             <p class="auth-form__policy-text">
                                 Bằng việc đăng ký, bạn đồng ý với Doctors về
                                 <a href="" class="auth-form__text-link">Điều khoản dịch vụ</a> &
                                 <a href="" class="auth-form__text-link">Chính sách bảo mật</a>
                             </p>
                         </div>
                         <div class="auth-form__control">
                             <span id="btn-back" class="btn btn-normal btn-signin">TRỞ LẠI</span>
                             <button class="btn btn--primary btn form-submit btn-signin">ĐĂNG KÝ</button>
                         </div>
                     </div>
                 </div>
                </div>
            </form>
            
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
    <script>
        var header = document.getElementById("Header");
        header.classList.add("scrolled");
    </script>
    <script>
        //output
        Validator({
            form: '#form-1',
            formGroupSelector: '.auth-form__group',
            errorSelector: '.form-message',
            rules: [
                Validator.isRequired('#ten-dangky', 'Vui lòng nhập tên người dùng'),
                Validator.isRequired('#email-dangky', 'Vui lòng nhập email người dùng'),
                Validator.isRequired('#matkhau-dangky', 'Mật khẩu không được để trống'),
                Validator.isRequired('#matkhau-confirm-dangky', 'Mật khẩu không được để trống'),
                Validator.isRequired('#sdt-dangky', 'Vui lòng nhập số điện thoại'),
                Validator.isPhone('#sdt-dangky'),
                Validator.isRequired('#ngaysinh-dangky', 'Vui lòng chọn ngày sinh'),
                Validator.isRequired('input[name="gioitinh"]', 'Vui lòng chọn giới tính'),
                Validator.isEmail('#email-dangky', 'Email không hợp lệ'),
                Validator.minLength('#matkhau-dangky',6),
                Validator.isConfirmed('#matkhau-confirm-dangky',function(){
                  return document.querySelector('#form-1 #matkhau-dangky').value;
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
      	    window.location.href="index.jsp";
      	});
      </script>
      <script>
	      const today = new Date();
	
	      // Tính toán ngày cách đây 18 năm
	      const minDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate());
	
	      // Định dạng ngày thành yyyy-mm-dd
	      const minDateString = minDate.toISOString().split('T')[0];
	
	      // Đặt giá trị tối thiểu cho input date
	      document.getElementById('ngaysinh-dangky').setAttribute('max', minDateString);
      </script>
</body>
</html>