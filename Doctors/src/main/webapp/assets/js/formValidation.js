//Event listener
//Json
//Fetch
//DOM location
//Local storage
//session storage
//coding convention 
//best practices


//Doi tuong validatoe
function Validator(options){
    function getParent(element, selector){
        while(element.parentElement){
            if(element.parentElement.matches(selector)){
                return element.parentElement;
            }else{
                element = element.parentElement;
            }
        }
    }

    var selectorRules = {};
    //Ham thuc hien validate
    function validate (inputElement, rule){
        var errorElement = getParent(inputElement, options.formGroupSelector).querySelector(options.errorSelector);
        var errorMessage;
        //lay ra cac rule cua selector
        var rules = selectorRules[rule.selector];
        //lap qua tung rule & kiem tra
        //neu co loi thi dung kiem tra
        for(var i = 0; i < rules.length; i++){
            switch(inputElement.type){
                case 'radio':
                case 'checkbox':
                    errorMessage = rules[i](
                        formElement.querySelector(rule.selector + ':checked')

                    );
                    break;
                default:
                    errorMessage = rules[i](inputElement.value);
            }
            
            if(errorMessage) break;
        }
        
        if(errorMessage){
            errorElement.innerText = errorMessage;
            getParent(inputElement, options.formGroupSelector).classList.add('invalid');
        }else{
            errorElement.innerText = '';
            getParent(inputElement, options.formGroupSelector).classList.remove('invalid');
        }
        return !errorMessage;
    }
    //lay element cua form can validate
    var formElement = document.querySelector(options.form);
    if(formElement){
        //khi submit form
        formElement.onsubmit = function(e){
            e.preventDefault();
            var isFormValid = true;
            options.rules.forEach(function(rule){
                var inputElement = formElement.querySelector(rule.selector);
                var isValid = validate(inputElement, rule);
                if(!isValid){
                    isFormValid = false;
                }
            });

            if(isFormValid){
                //submit voi javascript
                if(typeof options.onSubmit === 'function'){
                    var enableInputs = formElement.querySelectorAll('[name]');
                    var formValues = Array.from(enableInputs).reduce(function(values, input){
                        switch(input.type){
                            case 'radio':
                                values[input.name] = formElement.querySelector('input[name="' + input.name + '"]:checked').value;
                                break;
                            case 'checkbox':
                                if(!input.matches(':checked')) {
                                    values[input.name] = '';
                                    return values;
                                }
                                if(!Array.isArray(values[input.name])){
                                    values[input.name] = [];
                                }
                                values[input.name].push(input.value);
                                break;
                            case 'file':
                                values[input.name] = input.files;
                                break;
                            default:
                                values[input.name] = input.value;
                        }
                        return values;
                    },{});
                    options.onSubmit(formValues);
                }
                //submit voi hanh vi mac dinh
                else {
                    formElement.submit();
                }
            }
        }

        //Lap qua moi rule va xu ly su kien
        options.rules.forEach(function(rule){
            //luu lai rules cho moi inputelement
            if(Array.isArray(selectorRules[rule.selector])){
                selectorRules[rule.selector].push(rule.test);
            }else {
                selectorRules[rule.selector] = [rule.test];
            }
            
            var inputElements = formElement.querySelectorAll(rule.selector);
            Array.from(inputElements).forEach(function(inputElement){
                if(inputElement){
                    //xu ly blur
                    inputElement.onblur = function(){
                        validate(inputElement, rule);
                    }
                    //xu ly input
                    inputElement.oninput = function(){
                        var errorElement = getParent(inputElement, options.formGroupSelector).querySelector('.form-message');
                        errorElement.innerText = '';
                        getParent(inputElement, options.formGroupSelector).classList.remove('invalid');
                    }
                }
            });
        });
    }
}
//dinh nghia rule
//nguyen tac rule:
//1. co loi: tra ve msg loi
//2. khong loi: khong tra ve gi(undefined)
Validator.isRequired = function(selector,message){
    return {
        selector: selector,
        test: function(value){ 
            return (value)?undefined:message ||'Vui long nhap truong nay';
        }
    }
}

Validator.isEmail = function(selector,message) {
    return {
        selector: selector,
        test: function(value){ 
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value)?undefined:message ||'Email không hợp lệ';
        }
    }
}
Validator.isPhone = function(selector,message) {
    return {
        selector: selector,
        test: function(value){ 
            var regex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/g;
            return regex.test(value)?undefined:message ||'Số điện thoại không hợp lệ';
        }
    }
}
Validator.minLength = function(selector,min,message) {
    return {
        selector: selector,
        test: function(value){ 
            return value.length>=min?undefined:message ||`Độ dài mật khẩu từ ${min} kí tự trở lên`;
        }
    }
}
Validator.maxLength = function(selector,max,message) {
    return {
        selector: selector,
        test: function(value){ 
            return value.length<=max?undefined:message ||`Chỉ được nhập tối đa ${min} kí tự`;
        }
    }
}
Validator.isConfirmed = function(selector, getConfirmValue, message) {
    return {
        selector: selector,
        test: function(value){
            return value===getConfirmValue()?undefined: message ||'Gia tri nhap vao khong chinh xac';
        }
    }
}