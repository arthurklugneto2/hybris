<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="style.css">
            <link href="https://fonts.googleapis.com/css2?family=Abel&family=Montserrat:wght@300&display=swap"
                rel="stylesheet">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            <title>Login Form</title>
        </head>

        <style>
            * {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }

            a {
                color: limegreen;
                text-decoration: none;
            }

            body {
                height: 100vh;
                width: 100vw;
                background: linear-gradient(to right bottom, black, rgb(88, 173, 243));
                color: #ffffff;
                font-family: 'Abel', sans-serif;
            }

            th,
            td {
                text-align: center;
            }
        </style>

        <body>
            <div class="main" style="margin: 16px;">
                <div class="container">
                    <div class="gradient">
                        <div class="content">
                            <h1 style="margin: 16px;">Tour Details</h1>
                            <p style="margin: 16px;">Tour Details for ${tour.tourName}</p>
                            <h5 style="margin: 16px;">${tour.description}</h5>
                            <p style="margin: 16px;">Schedule:</p>
                            <table style="margin: 32px;">
                                <tr>
                                    <th width="200 | 10%">Venue</th>
                                    <th width="200 | 10%">Tipo</th>
                                    <th width="200 | 10%">Date</th>
                                </tr>
                                <c:forEach var="concert" items="${tour.concerts}">
                                    <tr>
                                        <td width="200 | 10%">${concert.venue}</td>
                                        <td width="200 | 10%">${concert.type}</td>
                                        <td width="200 | 10%">
                                            <fmt:formatDate pattern="dd MMM yyyy" value="${concert.date}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <a href="../bands">Back to Band List</a>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>