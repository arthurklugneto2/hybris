<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Abel&family=Montserrat:wght@300&display=swap" rel="stylesheet">
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
  </style>

  <body>
    <div class="main" style="margin: 16px;">
      <div class="container">
        <div class="gradient">
          <div class="content">
            <h1>${band.name}</h1>
            <br>
            <p>${band.description}</p>
            <br>
            <h3>Tipo de Musica</h3>
            <ul style="margin: 32px;">
              <c:forEach var="genre" items="${band.genres}">
                <li>${genre}</li>
              </c:forEach>
            </ul>
            <h3>Turnes</h3>
            <ul style="margin: 32px;">
              <c:forEach var="tour" items="${band.tours}">
                <li><a href="../tours/${tour.id}">${tour.tourName}</a>(number of concerts: ${tour.numberOfConcerts})
                </li>
              </c:forEach>
            </ul>
            <a href="../bands">Back to Band List</a>
          </div>
        </div>
      </div>
    </div>
  </body>

  </html>