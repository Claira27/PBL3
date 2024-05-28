<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctors</title>
    
    <link rel="stylesheet" href="assets/font/fontawesome-free-6.5.2-web/fontawesome-free-6.5.2-web/css/all.min.css">
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="assets/css/authenForm.css">
    <link rel="stylesheet" href="assets/css/dangky.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>
<body>
	<%
		Object ob = session.getAttribute("admin");
		Admin admin = null;
		if(ob!=null){
			admin = (Admin)ob;
		}
		if(admin == null){
	%>
	<a href="index.jsp" class="navbar__link uslide">Bạn chưa đăng nhập, quay lại trang chủ
        <i class="fa-solid fa-magnifying-glass navbar__search-icon"></i>
    </a>
    <%}else{ %>
    <div class="admin">
        <div class="grid">
            <div class="grid-2">
                <div class="menu">
                    <div class="menu-title">
                        <span class="left">DOC</span>
                        <span class="right">TORS</span>
                    </div>
                    <ul class="menu-list">
                        <li class="menu-item">
                            <a href="admin.jsp" class="menu-item-name">
                                <i class="fa-solid fa-chart-line menu-icon"></i>
                                Thống Kê
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="admin2.jsp" class="menu-item-name">
                                <i class="fa-solid fa-user-nurse menu-icon"></i>
                                Bác Sỹ
                            </a>
                        </li>
                        <li class="menu-item">
                            <span class="menu-item-current">
                                <i class="fa-regular fa-user menu-icon"></i>
                                Người Dùng
                            </span>
                        </li>
                        <li class="menu-item">
                            <a href="admin4.jsp" class="menu-item-name">
                                <i class="fa-solid fa-info menu-icon"></i>
                                Đổi Thông Tin
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="admin5.jsp" class="menu-item-name">
                                <i class="fa-solid fa-lock menu-icon"></i>
                                Đổi Mật Khẩu
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="" class="menu-item-name">
                                <i class="fa-solid fa-newspaper menu-icon"></i>
                                Tin Tức
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="dangxuat" class="menu-item-name">
                                <i class="fa-solid fa-house-user menu-icon"></i>
                                Đăng Xuất
                            </a>
                        </li>
                    </ul>
                    <div class="forhelp">
                        <p class="help-title">For Help ?</p>
                        <div class="help-info">
                            <p class="help-lable">Email: </p>
                            <p class="help-inf">support24/7@gmail.com</p>
                        </div>
                        <div class="help-info">
                            <p class="help-lable">Phone: </p>
                            <p class="help-inf">(+84)123 456 789</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="grid-10">
                <div class="content-admin">
                    <div class="navbar">
                        <div class="nav-group">
                            <i class="fa-solid fa-bars bar-icon"></i>
                            
                        </div>
                        <div class="nav-group">
                            <div class="noti-bell">
                                <i class="fa-solid fa-bell noti"></i>
                            </div>
                            <div class="noti-mail">
                                <i class="fa-solid fa-envelope noti"></i>
                            </div>
                            <div class="admin-header">
                                <i class="fa-solid fa-user-tie admin-icon"></i>
                                <span class="admin-name"><%=admin.getName().toUpperCase()%></span>
                            </div>
                            <div class="setting">
                                <i class="fa-solid fa-gear noti"></i>
                            </div>
                        </div>
                    </div>
                    <div class="group3">
                        <div class="form-wrap--admin">
                            <form action="dang-ky" id="form-9" method="POST">
                                <div class="dang-ky">
                                    <div class="auth-form__container">
                                        <div class="auth-form__header">
                                            <h3 class="auth-form__heading">THÔNG TIN NGƯỜI DÙNG</h3>
                                        </div>
                                        <span class="form-message invalid" style="color: red;" id = "baoloi"></span>
                                        <div class="auth-form__info">
                                            <div class="auth-form__form auth-form__form--dangky">
                                                <div class="auth-form__group">
                                                    <label for="ten-dangky" class="form-label">Họ và tên</label>
                                                    <input type="text" class="auth-form__input" name="ten-dangky" id="ten-dangky" value="">
                                                    <span class="form-message"></span>
                                                </div>
                                                <div class="auth-form__group">
                                                    <label for="email-dangky" class="form-label">Email</label>
                                                    <input type="text" class="auth-form__input" name="email-dangky" id="email-dangky" value="">
                                                    <span class="form-message"></span>
                                                </div>
                                                <div class="auth-form__group">
                                                    <label for="matkhau-dangky" class="form-label">Mật khẩu</label>
                                                    <input type="text" class="auth-form__input" name="matkhau-dangky" id="matkhau-dangky" placeholder="doctors" value="doctors">
                                                    <span class="form-message"></span>
                                                </div>
                                            </div>
                                            <div class="auth-form__form auth-form__form--dangky">
                                                <div class="auth-form__group">
                                                    <label for="diachi-dangky" class="form-label">Địa chỉ</label>
                                                    <input type="text" class="auth-form__input" name="diachi-dangky" id="diachi-dangky" value="">
                                                    <span class="form-message"></span>
                                                </div>
                                                <div class="auth-form__group">
                                                    <label for="sdt-dangky" class="form-label">Số điện thoại</label>
                                                    <input type="text" class="auth-form__input" name="sdt-dangky" id="sdt-dangky" value="">
                                                    <span class="form-message"></span>
                                                </div>
                                                <div class="auth-form__group">
                                                    <label for="ngaysinh-dangky" class="form-label">Ngày sinh</label>
                                                    <input type="Date" class="auth-form__input" name="ngaysinh-dangky" id="ngaysinh-dangky" value="">
                                                    <span class="form-message"></span>
                                                </div>
                                                <div class="auth-form__group">
                                                    <label for="gioitinh-dangky" class="form-label">Giới tính</label>
                                                    <div class="gioitinh-wrap">
                                                        <input type="radio" id="nam" name="gioitinh" value="nam">
                                                        <span class="gioitinh-title">Nam</span>
                                                        <input type="radio" id="nu" name="gioitinh" value="nu">
                                                        <span class="gioitinh-title">Nữ</span>
                                                        <input type="radio" id="khac" name="gioitinh" value="khac">
                                                            <span class="gioitinh-title">Khác</span>
                                                        </div>
                                                        <span class="form-message"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="auth-form__control">
                                                <a href="admin3.jsp" class="btn btn-normal btn-signin">TRỞ LẠI</a>
                                                <button class="btn btn--primary btn form-submit btn-signin">CẬP NHẬT</button>
                                            </div>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%} %>
</body>
</html>