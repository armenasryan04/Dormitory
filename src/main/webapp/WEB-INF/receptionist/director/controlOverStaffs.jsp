<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="dormitory.models.Student" %>
<%@ page import="dormitory.models.Receptionist" %>
<html>
<head>
    <title>List of Rooms</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<%boolean isAllowed = (Boolean) request.getAttribute("isAllowed");%>
<body>

<div class="wave"></div>
<div class="wave"></div>
<div class="wave"></div>
<div class="forming">
    <div class="title">STUDENTS LIST <p style="font-size: 15px; font-weight: 0">STUDENT'S TOTAL
        NUMBER <%=request.getAttribute("numberOfStudents")%>
    </p></div>
    <div class="container">

        <form id="searchForm" action="/control" method="get">
            <div class="search-box">
                <div class="input-search-background">
                    <input type="hidden" name="status" value="<%=request.getAttribute("inArchive")%>">
                    <div class="btn-search">
                        <input type="text" name="search" class="input-search animate" placeholder="🔍 search..."
                               id="searchInput" value="${not empty param.search ? param.search : ''}">
                    </div>

                </div>
            </div>
        </form>
        <br/>
        <table class="table">
            <thead>
            <tr>
                <th>Inspection Booklet Number</th>
                <th>NAME</th>
                <th>SURNAME</th>
                <th>PHONE</th>
                <th>E-Mail</th>
                <th>END DATE</th>
                <th style="padding-left: 10px">REMAINING DAYS</th>
                <th>ROOM INFO</th>
                <%if (request.getAttribute("inArchive") != null) { %>
                <th>Activate</th>
                <%
                    }
                    ;
                %>
            </tr>
            </thead>
            <tbody>
            <% if (students != null && !students.isEmpty()) { %>
            <% for (Student student : students) { %>
            <tr>
                <td>
                    <%=student.getId()%>
                </td>
                <td><%= student.getName() %>
                </td>
                <td><%= student.getSurname() %>
                </td>
                <td><%=student.getPhoneNum()%>
                </td>
                <td>
                    <%=student.getEmail()%>
                </td>
                <%if (student.getDaysUntil().equals(0 + "d " + 0 + "h")) {%>
                <td style="color: #650404"><%= student.getDeadline() %>
                </td>
                <td style="padding: 5px; color: #5d0202"><%= student.getDaysUntil() %>
                        <%}else {%>

                <td><%=student.getDeadline()%>
                </td>
                <td style="padding: 5px;"><%= student.getDaysUntil() %>
                </td>
                <%}%>
                <td style="padding-left: 2px "><a href="/roomsInfo?id=<%=student.getId()%>"
                                                  class="gradient-button">Room</a>
                </td>
                <%if (request.getAttribute("inArchive") != null) {%>
                <td style="padding-left: 2px "><a href="/freeRooms?id=<%=student.getId()%>" class="gradient-button"><i
                        style="font-size: 20px" class='bx bx-refresh'></i></a>
                </td>
                <%
                    }
                    ;
                %>

            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<div class="wrapper">

    <span class="menu"><i style="font-size:44px; " class='bx bx-menu'></i></span>

    <div class="overlay">
        <a style="position: absolute;top:5px " class="gradient-button" href="/logout"><i class='bx bx-log-out'></i></a>
        <ul>
            <li><a href="/directorControl" ><i class='bx bxs-home'></i></a></li>
            <li><a href="/listOfStaffs">List Of Staffs</a></li>
            <li><a href="/listOfStaffs?status=archive">Staffs Archive</a></li>
            <li><a href="/ListOfRegistrants">Registrants</a></li>
            <li>Allow Registration</li>
            <label class="switch">
                <input type="checkbox">
                <a class="slider" id='allow'></a>
            </label>
        </ul>
    </div>
    <div class="blurry-background"></div>
</div>
</body>
<style type="text/css">
    .switch {
        --secondary-container: #3a4b39;
        --primary: #84da89;
        font-size: 17px;
        position: relative;
        display: inline-block;
        width: 3.7em;
        height: 1.8em;
    }

    .switch input {
        display: none;
        opacity: 0;
        width: 0;
        height: 0;
    }

    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    <%if (!isAllowed){%> background-color: #313033;
    <%} else {%> background-color: var(--primary);
    <%}%> transition: .2s;
        border-radius: 30px;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 1.4em;
        width: 1.4em;
        border-radius: 20px;
    <%if (!isAllowed){%> background-color: #aeaaae;
        left: 0.2em;
    <%} else {%> right: 0.2em;
        background-color: var(--secondary-container);
    <%}%> bottom: 0.2em;
        transition: .4s;
    }

    input:checked + .slider::before {
    <%if (!isAllowed){%> background-color: var(--secondary-container);
    <%} else {%> background-color: var(--primary);
    <%}%>
    }

    input:checked + .slider {
    <%if (!isAllowed){%> background-color: var(--primary);
    <%} else {%> background-color: var(--secondary-container);
    <%}%>
    }

    input:focus + .slider {
        box-shadow: 0 0 1px var(--secondary-container);
    }

    input:checked + .slider:before {
    <%if (!isAllowed){%> transform: translateX(1.9em);
    <%} else {%> transform: translateX(-1.9em);
    <%}%>
    }

    .container {
        max-width: 1200px;
        width: auto;
        max-height: 80%;
        background: linear-gradient(135deg, #0cffe5, #36b7ef);
        padding: 20px 35px;
        border-radius: 0px 0px 20px 20px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
        backdrop-filter: blur(10);
        overflow-y: auto;
        z-index: 100;
    }

    .forming {
        max-width: 1200px;
        width: auto;
        background: transparent;
        backdrop-filter: blur(10);
        z-index: 100;
    }

    .forming .title {
        font-size: 35px;
        font-weight: 600;
        text-align: center;
        line-height: 30px;
        padding-top: 20px;
        color: #ffffff;
        user-select: none;
        border-radius: 15px 15px 0 0;
        background: linear-gradient(135deg, #36b7ef, #a436ed);
    }


    .container::-webkit-scrollbar {
        width: 10px;
        border-radius: 5px;
    }

    .container::-webkit-scrollbar-track {
        background: linear-gradient(135deg, #a436ed, #36b7ef);
        border-radius: 5px;
    }

    .container::-webkit-scrollbar-thumb {
        background: linear-gradient(1355deg, #00fde8, #428af6);
        background-size: 12px;
        border-radius: 5px;
        transition: background .5s;
    }

    .container::-webkit-scrollbar-thumb:hover {
        background: linear-gradient(135deg, #00fd9c, #428af6);
    }

    .table {
        width: 100%;
        margin-bottom: 50px;
        border-radius: 5px;
        border: 0px solid #ffffff;
        border-top: 0px solid #ffffff;
        border-bottom: 0px solid #fff;
        border-collapse: collapse;
        outline: 5px solid #ffd300;
        font-size: 20px;
        background: #ffffff !important;
    }

    .table th {
        font-weight: bold;
        padding: 3px 16px;
        background: #ffd300;
        border: 6px none;
        text-align: left;
        font-size: 15px;
        border-top: 3px solid #ffd300;
        border-bottom: 3px solid #ffd300;
    }

    .table td {
        border: 30px;
        font-size: 16px;
        white-space: nowrap;
        padding: 2px 10px;
        background: linear-gradient(135deg, #fdd100, #428af6);
    }

    .table tbody tr:nth-child(even) {
        background: #4c698d !important;
    }

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
        top: 20px;
        right: 20px;
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
        transition: .5s;
    }

    .gradient-button:hover {
        background-position: right center;
        color: rgb(0, 0, 0);
        box-shadow: 0 0 10px #f519f5;
        font-size: 35px;
    }

    * {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }

    body {
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #6259e7;
        font-family: 'Lato', sans-serif;
    }

    .search-box {
        position: relative;
    }


    .input-search {
        color: black;
        width: 63px;
        height: 40px;
        padding: 10px 20px;
        border: none;
        background: linear-gradient(135deg, #fdd100, #428af6);
        border-radius: 40px;
        outline: none;
        font-size: 18px;
        -webkit-background-clip: text;
        background-clip: text;
        transition: all 0.5s ease-in-out;
    }

    .input-search-background {
        width: 63px;
        height: 40px;
        border: none;
        border-radius: 40px;
        outline: none;
        font-size: 18px;
        background: linear-gradient(135deg, #fdd100, #428af6);
        transition: all 0.5s ease-in-out;

    }

    .input-search::placeholder {
        color: rgb(2, 0, 0);
        font-size: 16px;
        font-weight: 100;
    }


    .input-search-background:focus-within {
        border-radius: 10px;
        width: 300px;
    }

    .btn-search:focus + .input-search,
    .input-search:focus {
        width: 300px;
        border-radius: 10px;
        background-color: transparent;
        caret-color: #2c0248;
        border-bottom: 2px solid rgb(59, 75, 255);
        animation: textColorChange 0.5s ease-in-out forwards;
    }

    .input-search:hover {
        caret-color: #85f100;
        width: 300px;
    }

    .input-search-background:hover {
        width: 300px;
    }

    .wrapper {
        width: 100%;
        height: 100vh;
        position: absolute;
        overflow: hidden;
    }

    .wrapper i {
        font-size: 25px;
    }

    .wrapper span {
        z-index: 999955887;
        position: absolute;
        top: 10px;
        left: 20px;
        width: 64px;
        height: 43px;
        color: #4907bb;
        display: inline-block;
        padding: 0px 9px;
        margin: auto;
        border-radius: 10px;
        cursor: pointer;
        background-image: linear-gradient(to right, #428af6 0%, #fdd100 51%, rgb(80, 0, 241) 100%);
        background-size: 200% auto;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.31);
        transition: text-shadow 0.5s ease, .5s;
    }

    .wrapper span:hover {
        background-position: right center;
        color: rgb(0, 0, 0);
        box-shadow: 0 0 10px #f519f5;
    }

    .wrapper .overlay {
        position: absolute;
        left: 0;
        right: 75%;
        bottom: 0;
        width: 25%;
        height: 100%;
        background: linear-gradient(rgba(0, 241, 76, 0.82), rgba(73, 7, 187, 0.84));
        z-index: 9955;
    }

    .blurry-background {
        position: absolute;
        top: 0;
        right: 0;
        width: 100%;
        height: 100%;
        backdrop-filter: blur(10px);
        -webkit-backdrop-filter: blur(10px);
        z-index: 100;
        opacity: 1;
    }


    .wrapper .overlay ul {
        width: 100%;
        text-align: center;
        margin-top: 100px;
        padding-left: 0;
        margin-left: -10px;
        font-size: 1em;
        font-weight: 800;
        user-select: none;
    }

    .wrapper .overlay ul li {
        margin: 10px 0;
        transition: all 1s;
    }

    .wrapper .overlay ul li:hover {
        text-shadow: #f519f5 1px 0 10px;
    }

    .wrapper .overlay ul li a {
        text-decoration: none;
        color: #000000;
        position: relative;
        display: inline-block;
        padding: 20px 0;
        overflow: hidden;
    }

    .wrapper .overlay ul li a:after {
        display: block;
        border-radius: 2px;
        content: '';
        left: 0;
        bottom: -10px;
        height: 5px;
        background: #4e00ce;
        transform: translateX(-101%);
    }

    .wrapper .overlay ul li a:hover:after {
        animation: border-anim 0.5s 1 ease normal;
        transform: translateX(0);
    }

    @keyframes menu-anim {
        100% {
            bottom: 0;
            left: 0;
            width: 25%;
        }
    }

    @-webkit-keyframes border-anim {
        0% {
            transform: translateX(-100%);
        }

        100% {
            transform: translateX(0);
        }
    }

    .icon {
        width: 38px;
        height: 46px;
        background: transparent;
        text-shadow: 0 0 20px rgba(0, 0, 0, 0);;
        z-index: 100;
        white-space: nowrap;
        text-decoration: none;
        color: #000000;
        position: relative;
        display: inline-block;
        margin: 10px 0;
        overflow: hidden;
        transition: all 2.5s ease;
    }

    .icon i {
        font-size: 40px;
        color: rgba(0, 0, 0, 0.99);
        text-shadow: 0 0 20px rgba(0, 0, 0, 0);
        transition: all 0.5s ease;
    }

    .icon:hover {
        width: 370px;
    }

    .icon i:hover {
        color: #000000;
        text-shadow: #f519f5 1px 0 20px;
    }


</style>
<script>
    $(document).ready(function () {
        $('.menu').click(function () {
            event.preventDefault();
            window.location.href = '/loginConductor';
        });
        $(document).click(function (event) {
            if (!$(event.target).closest('.overlay').length && !$(event.target).closest('.menu').length) {
                event.preventDefault();
                window.location.href = '/loginConductor';
            }
        });
    });
    document.addEventListener('DOMContentLoaded', function () {
        var allowButton = document.getElementById('allow');

        allowButton.addEventListener('click', function () {
            setTimeout(function () {
                window.location.href = '/allowRegistration';
            }, 220);
        });
    });

</script>
</html>