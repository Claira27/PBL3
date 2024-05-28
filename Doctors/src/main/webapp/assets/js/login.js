/**
 * 
 */
// Function to create login form modal
function createLoginForm() {
    const loginFormHTML = `
        <!-- Modal layout -->
        <div class="modal" id="modal">
            <div class="modal__overlay"></div>
            <div class="modal__body">
                <!-- Sign-in form -->
                <form action="dangnhap" id="form-2" method="POST">
                    <div class="auth-form">
                        <div class="auth-form__container">
                            <div class="auth-form__header">
                                <h3 class="auth-form__heading">Đăng nhập</h3>
                                <a class="auth-form__switch-btn" href="dangky.jsp" style="text-decoration:none">Đăng ký</a>
                            </div>
                            <span class="form-message invalid"  style="color: red;" id="baoloi-dangnhap"></span>
                            <div class="auth-form__form">
                                <div class="auth-form__group">
                                    <input type="text" class="auth-form__input" placeholder="hongvyho123@gmail.com" name="email-dangnhap" id="email-dangnhap">
                                    <span class="form-message"></span>
                                </div>
                                <div class="auth-form__group">
                                    <input type="password" class="auth-form__input" placeholder="Mật khẩu của bạn" name="matkhau-dangnhap" id="matkhau-dangnhap">
                                    <span class="form-message"></span>
                                </div>
                                <div class="auth-form__group">
								    <input type="checkbox" class="auth-form__input" name="isdoctor" id="isdoctor" style="width:14px;height:14px;margin-right:14px;position:relative;top:2px;">
								    <label for="isdoctor" class="form-label" style="font-style:Italic;">Đăng nhập với tư cách bác sỹ</label>
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
                                <button class="btn btn-normal auth-form__control--back">TRỞ LẠI</button>
                                <button class="btn btn--primary form-submit">ĐĂNG NHẬP</button>
                            </div>
                        </div>
                        <div class="auth-form__footer">
                            <img src="https://dynamic.design.com/asset/logo/53e9d92f-933e-4380-ad53-d1e91a94b16f/logo-search-grid-1x?logoTemplateVersion=1&v=637907166083400000&text=T%c3%acm+ki%e1%ba%bfm+b%c3%a1c+s%e1%bb%b9&colorpalette=blue" alt="Tìm kiếm Bác Sỹ" class="header__logo-img">
                            <span class="auth-form__footer-sologan">
                                <p>"Đối Tác Sức Khỏe Uy Tín - Tìm Hiểu Thêm về</p>
                                <p>Bác Sỹ của Bạn Ngay Hôm Nay!"</p>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    `;

    // Add the modal to the body
    document.body.innerHTML += loginFormHTML;
}

createLoginForm();
const modal = document.querySelector('#modal')


// Function to clear form fields
function clearLoginForm() {
    document.getElementById('email-dangnhap').value = '';
    document.getElementById('matkhau-dangnhap').value = '';
    document.querySelector('#baoloi-dangnhap').textContent = '';
    document.getElementById('matkhau-dangnhap').classList.remove('invalid');
    document.getElementById('email-dangnhap').classList.remove('invalid');
}

//Function to close 
function closeModal(){
	modal.style.display = "none";
	clearLoginForm();
}

// Add event listener for the back button
var backBtn = modal.querySelector('.auth-form__control--back');
backBtn.addEventListener('click', function(event) {
	event.preventDefault();
    closeModal();
});

// Initialize form validation
Validator({
    form: '#form-2',
    formGroupSelector: '.auth-form__group',
    errorSelector: '.form-message',
    rules: [
        Validator.isRequired('#email-dangnhap', 'Vui lòng nhập email người dùng'),
        Validator.isRequired('#matkhau-dangnhap', 'Mật khẩu không được để trống'),
        Validator.isEmail('#email-dangnhap', 'Email không hợp lệ'),
        Validator.minLength('#matkhau-dangnhap', 6),
    ],
    onSubmit: function(data){
		console.log(data);
		fetch('dangnhap', {
		    method: 'POST',
		    body: JSON.stringify(data),
		    headers: {
		        "Content-type": "application/json; charset=UTF-8"
		    }
		})
		.then(response => {
		    if (!response.ok) {
		        throw new Error('Network response was not ok');
		    }
		    return response.json();
		})
		.then(responseData => {
		    console.log(responseData);
		    if (responseData && responseData.success) {
		        if (responseData.account === 0) {
		            window.location.href = "admin.jsp";
		        } else if (responseData.account === 1) {
		            window.location.href = "index.jsp";
		        } else {
		            window.location.reload();
		        }
		    } else {
		        const errorMessage = responseData.error;
		        if (errorMessage) {
		            const errorElement = document.querySelector('#baoloi-dangnhap');
		            errorElement.textContent = errorMessage;
		        }
		    }	
		})
		.catch(error => {
		    console.error('Error:', error);
		});
	}
});

// Get the login link element
const loginlink = document.querySelector("#loginLink");

// Function to handle login link click
function handleLoginLinkClick() {
    modal.style.display = "flex";
}

// Add a click event listener to the login link
loginlink.addEventListener('click', handleLoginLinkClick);