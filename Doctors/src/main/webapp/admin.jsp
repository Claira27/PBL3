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
                            <span class="menu-item-current">
                                <i class="fa-solid fa-chart-line menu-icon"></i>
                                Thống Kê
                            </span>
                        </li>
                        <li class="menu-item">
                            <a href="admin2.jsp" class="menu-item-name">
                                <i class="fa-solid fa-user-nurse menu-icon"></i>
                                Bác Sỹ
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="admin3.jsp" class="menu-item-name">
                                <i class="fa-regular fa-user menu-icon"></i>
                                Người Dùng
                            </a>
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
                    <div class="content-title">Thống Kê</div>
                    <div class="group1">
                        <div class="group1-item">
                            <div class="g1-main">
                                <p class="g1-1">Người Dùng</p>
                                <p id="g1-nguoidung" class="g1-1-num"></p>
                                <p class="g1-1">Bác Sỹ</p>
                                <p id="g1-bacsy" class="g1-2-num"></p>
                            </div>
                            <div class="g1-img">
                                <i class="fa-solid fa-chart-area g1-icon"></i>
                            </div>
                        </div>
                        <div class="group1-item">
                            <div class="g1-main">
                                <p class="g1-header">Tìm Kiếm</p>
                                <p id="g1-timkiem" class="g1-num"></p>
                                <p class="g1-fac">Hôm nay</p>
                            </div>
                            <div class="g1-img">
                                <i class="fa-solid fa-rotate g1-icon"></i>
                            </div>
                        </div>
                        <div class="group1-item">
                            <div class="g1-main">
                                <p class="g1-header">Đặt Lịch</p>
                                <p id="g1-datlich" class="g1-num"></p>
                                <p class="g1-fac">Trong tháng</p>
                            </div>
                            <div class="g1-img">
                                <i class="fa-solid fa-dice-d6 g1-icon"></i>
                            </div>
                        </div>
                        <div class="group1-item">
                            <div class="g1-main">
                                <p class="g1-header">Tư Vấn</p>
                                <p id="g1-tuvan" class="g1-num"></p>
                                <p class="g1-fac">Trong tháng</p>
                            </div>
                            <div class="g1-img">
                                <i class="fa-solid fa-file-invoice-dollar g1-icon"></i>
                            </div>
                        </div>
                    </div>
                    <div class="group2">
                        <div class="group2-item">
                            <img src="assets/img/diagram1.png" alt="diagram1">
                        </div>
                        <div class="group2-item">
                            <img src="assets/img/diagram2.png" alt="diagram2">
                        </div>
                        <div class="group2-item">
                            <img src="assets/img/diagram3.png" alt="diagram3">
                        </div>
                    </div>
                    <div class="group3">
                        <div class="group3-item">
                            <div class="g3-title">Người Dùng Tích Cực</div>
                            <div class="g3-header">
                                <span class="g3 c1">#</span>
                                <span class="g3 c2">Người Dùng</span>
                                <span class="g3 c3">Ngày Sinh</span>
                                <span class="g3 c4">Tỉnh Thành</span>
                                <span class="g3 c5">Lượt Đặt</span>
                            </div>
                            <div id="g3-nguoidung" class="g3-body">
                                
                            </div>
                        </div>
                        <div class="group3-item">
                            <div class="g3-title">Bác Sỹ Phổ Biến</div>
                            <div class="g3-header">
                                <span class="g3 c6">#</span>
                                <span class="g3 c7">Bác Sỹ</span>
                                <span class="g3 c8">Bệnh Viện</span>
                                <span class="g3 c9">Lượt Đặt</span>
                            </div>
                            <div id="g3-bacsy" class="g3-body">
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="assets/js/adminthongke.js"></script>
    <%} %>
</body>
</html>