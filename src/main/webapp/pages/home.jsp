<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cash Register</title>
    <style>
        <%@include file="css/style.css" %>
    </style>
    <script>
        var i = 0;
        function duplicate() {
            var original = document.getElementById('products' + i);
            var clone = original.cloneNode(true);
            clone.id = "products" + ++i;
            original.appendChild(clone);
        }
    </script>
</head>
<body>
<section id="box">
    <section id="head">
        <h1>Cash Register</h1>
    </section>
    <section id="mid">
        <form id="myForm" action="receipt" method="get">
            <h1>Add products to your cart:</h1>
            <div id="products0">
                <select id="list" name="list">
                    <option value="" selected disabled hidden>Choose product</option>
                    <option value="Apple">Apple</option>
                    <option value="Orange">Orange</option>
                    <option value="Banana">Banana</option>
                    <option value="Potato">Potato</option>
                    <option value="Tomato">Tomato</option>
                    <option value="Onion">Onion</option>
                    <option value="Milk">Milk</option>
                    <option value="Cheese">Cheese</option>
                    <option value="Butter">Butter</option>
                    <option value="Pork">Pork</option>
                    <option value="Steak">Steak</option>
                    <option value="Bread">Bread</option>
                    <option value="Cereals">Cereals</option>
                </select>
            </div>
            <br>
            <button type="button" onclick="duplicate()">NEXT PRODUCT</button>
            <input type="submit" value="GENERATE RECEIPT">


            <%--
                            <ul style="color: white">
                                <input type="text" name= "list" value="list">
                            <option value="">Apple </option >
                            <option value=""> Orange</option >
                            <option value=""> Banana</option >
                            <option value=""> Potato</option >
                            <option value=""> Tomato</option >
                            <option value=""> Onion</option >
                            <option value=""> Milk</option >
                            <option value=""> Cheese</option >
                            <option value=""> Butter</option >
                            <option value=""> Pork</option >
                            <option value=""> Steak</option >
                            <option value=""> Bread</option >
                            <option value=""> Cereals</option >
                            </ul>
            --%>


        </form>
        <h1>Receipt is generated in JSON</h1>
    </section>
    <section id="bot">
    </section>

</section>
</body>
</html>

