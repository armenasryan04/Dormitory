<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="dormitory.models.Receptionist" %>
<html>
<%
    Receptionist receptionist = (Receptionist) request.getSession().getAttribute("receptionist");
    String name;
    String surname;
    String phone;
    String email;
    String birthday;
    if (receptionist != null) {
        name = receptionist.getName();
        surname = receptionist.getSurname();
        phone = receptionist.getPhone();
        email = receptionist.getEmail();
        birthday = receptionist.getBirthday().toString();
    } else {
        name = "";
        surname = "";
        phone = "";
        email = "";
        birthday = "";
    }
%>
<head>
    <title>Sign Up</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>

<div class="wave"></div>
<div class="wave"></div>
<div class="wave"></div>
<br/>
<a href="/login" class="gradient-button">Back</a>
<div class="wrapper-data">
    <div class="title">Sign Up</div>

    <form action="/createPassword" method="post">
        <div class="field">
            <input type="text" required name="name" value="<%=name%>">
            <label class="input-box">Name</label>
        </div>
        <div class="field">
            <input type="text" required name="surname" value="<%=surname%>">
            <label class="input-box" style=" float: right;">Surname</label>
        </div>
        <div class="field">
            <input type="text" required name="phone" value="<%=phone%>">
            <label class="input-box">Phone Number</label>
        </div>
        <div class="field">
            <input type="email" required name="email" value="<%=email%>">
            <label class="input-box">E-Mail</label>
        </div>
        <div class="field">
            <input type="date" name="birthday" value="<%=birthday%>">
            <label class="input-box">Birthday</label>
        </div>
        <br>
        <div class="gender">
            <label >Gender</label>
            <select name="gender">
                <option style=" color: white;" value="null">Choose</option>
                <option value="MALE" style="background:#36b7ef;">MALE</option>
                <option value="FEMALE" style="background: #ff7aff;">FEMALE</option>
            </select>
        </div>
        <p style="color: #6e0025; padding:0  115px;">not required!</p>

        <div class="field">
            <input type="text" name="experienceInformation">
            <label>Your Experience</label>
        </div>
        <div class="field">
            <br/> <input type="submit" value="Sign Up">
        </div>

    </form>
        <% if (request.getAttribute("errMsg") != null) { %>
    <div id="errorContainer" class="error-container">
        <div id="errorMessage" class="error-message">
            <p><%= request.getAttribute("errMsg") %>
            </p>
        </div>
        <% } %>
    </div>

</body>
<style type="text/css">

    .wave {
        background: rgb(255 255 255 / 25%);
        border-radius: 1000% 1000% 0 0;
        position: fixed;
        width: 200%;
        height: 12em;
        animation: wave 10s -3s linear infinite;
        transform: translate3d(0, 0, 0);
        opacity: 0.8;
        bottom: 0;
        left: 0;
        z-index: -1;
    }

    .wave:nth-of-type(2) {
        bottom: -1.25em;
        animation: wave 18s linear reverse infinite;
        opacity: 0.8;
    }

    .wave:nth-of-type(3) {
        bottom: -2.5em;
        animation: wave 20s -1s reverse infinite;
        opacity: 0.9;
    }

    @keyframes wave {
        2% {
            transform: translateX(0%);
        }

        25% {
            transform: translateX(-25%);
        }

        50% {
            transform: translateX(-50%);
        }

        75% {
            transform: translateX(-25%);
        }

        100% {
            transform: translateX(0%);
        }
    }

    body {
        font-family: -apple-system, BlinkMacSystemFont, sans-serif;
        overflow: auto;
        background: linear-gradient(315deg, rgba(101, 0, 94, 1) 3%, rgba(60, 132, 206, 1) 38%, rgba(48, 238, 226, 1) 68%, rgba(255, 25, 25, 1) 98%);
        animation: gradient 15s ease infinite;
        background-size: 400% 400%;
        background-attachment: fixed;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
    }

    @keyframes gradient {
        0% {
            background-position: 0% 0%;
        }
        50% {
            background-position: 100% 100%;
        }
        100% {
            background-position: 0% 0%;
        }
    }

    .gradient-button {
        position: absolute;
        top: 10px;
        left: 20px;
        text-decoration: none;
        color: #4907bb;
        display: inline-block;
        padding: 10px 20px;
        margin: 5px 15px;
        border-radius: 10px;
        text-transform: uppercase;
        letter-spacing: 2px;
        background-image: linear-gradient(to right, #428af6 0%, #fdd100 51%, rgb(80, 0, 241) 100%);
        background-size: 200% auto;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.31);
        transition: all 0.5s ease;
    }

    .gradient-button:hover {
        background-position: right center;
        color: rgb(0, 0, 0);
        box-shadow: 0 0 10px #f519f5;
    }


    .wrapper-data {
        width: 380px;
        background: linear-gradient(135deg, rgba(165, 54, 239, 0.44), #00878c);
        border-radius: 15px;
        box-shadow: 0px 15px 20px rgba(0, 0, 0, 0.1);
        z-index: 99;
    }

    .wrapper-data .title {
        font-size: 35px;
        font-weight: 600;
        text-align: center;
        line-height: 100px;
        color: #fff;
        user-select: none;
        border-radius: 15px 15px 0 0;
        background: linear-gradient(135deg, #a436ed, #36b7ef);
    );
    }

    .wrapper-data form {
        padding: 10px 30px 50px 30px;
    }

    .wrapper-data form .field {
        height: 50px;
        width: 100%;
        margin-top: 20px;
        position: relative;
    }

    .wrapper-data form .field input {
        height: 100%;
        width: 100%;
        outline: none;
        font-size: 17px;
        padding-left: 20px;
        border: 1px solid lightgrey;
        border-radius: 25px;
        transition: all 0.3s ease;
        background: rgba(157, 241, 217, 0.29);
    }

    .wrapper-data form .field input:focus,
    form .field input:valid {
        border-color: #c7af62;
    }

    .wrapper-data form .field label {
        position: absolute;
        top: 50%;
        left: 20px;
        color: #151b2c;
        font-weight: 400;
        font-size: 17px;
        pointer-events: none;
        transform: translateY(-50%);
        transition: all 0.3s ease;
    }
    .gender {
        display: inline-block;
    }

    .gender label {

        margin-bottom: 5px;
    }

    .gender select {
        cursor: pointer;
        border: 0;
        outline: 0;
        border-radius: 40px;
        background:linear-gradient(135deg, #36b7ef, #a436ed);
        font-size: 15px;
        color: black;
        padding: 0 15px ;
        transition: 0.2s all ;
    }
    .gender option{
        background: rgb(3, 83, 204);
    }
    .gender select:hover{
        box-shadow: 0 0 10px #f519f5;
    }


    form .field input:focus ~ label,
    form .field input:valid ~ label {
        top: 0%;
        font-size: 14px;
        color: rgba(30, 2, 166, 0.4);
        background: linear-gradient(135deg, #a436ed, #36b7ef);
        border-radius: 7px;
        transform: translateY(-50%);
    }


    form .content input {
        width: 15px;
        height: 15px;
        background: red;
    }

    form .content label {
        color: #1b50a2;
        user-select: none;
        padding-left: 5px;
    }

    form .field input[type="submit"] {
        color: #4907bb;
        border: none;
        padding-left: 0;
        margin-top: -10px;
        font-size: 20px;
        font-weight: 500;
        cursor: pointer;
        background-image: linear-gradient(to right, #6ee547, #4158d0);
        background-size: 200% auto;
        transition: all 0.5s ease;
    }

    form .field input[type =  "submit"]:hover {
        background-position: right center;
        color: #6ee547;
    }

    form .field input[type="submit"]:active {
        transform: scale(0.95);
    }


    form .pass-link a,
    form .signup-link a {
        color: rgba(172, 208, 65, 0.76);
        text-decoration: none;
    }

    form .pass-link a:hover,
    form .signup-link a:hover {
        text-decoration: underline;
    }

    .error-container {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        backdrop-filter: blur(3px);
        justify-content: center;
        align-items: center;
    }

    .error-message {
        color: white;
        height: auto;
        width: auto;
        background-color: rgb(114, 3, 3);
        padding: 20px;
        border-radius: 7px;
    }
</style>
<script>

    function handleButtonClick() {
        var errorContainer = document.getElementById('errorContainer');
        var errorMessage = document.getElementById('errorMessage');
        if (errorContainer && errorContainer.contains(event.target) && !errorMessage.contains(event.target)) {
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
            window.location.replace('/signUp');
        }
    };

    function handleEnterKeyPress() {
        if (event.key === 'Enter' || event.keyCode === 32) {

            var errorContainer = document.getElementById('errorContainer');
            var errorMessage = document.getElementById('errorMessage');
            errorContainer.style.display = 'none';
            errorMessage.style.display = 'none'
            window.location.replace('/signUp');
        }
    }

    <% if (request.getAttribute("errMsg") != null) { %>
    document.getElementById('errorMessage').style.display = 'flex';
    document.getElementById('errorContainer').style.display = 'flex';
    <% } %>
    document.body.addEventListener('keypress', handleEnterKeyPress)
    document.body.addEventListener('click', handleButtonClick);
</script>

</html>
