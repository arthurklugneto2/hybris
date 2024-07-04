<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!doctype html>
  <style>
    @import url("https://fonts.googleapis.com/css2?family=Oswald:wght@500&display=swap");

    body {
      font-family: 'Abel', sans-serif;
      background: linear-gradient(to right bottom, black, rgb(88, 173, 243));
      color: #ffffff;
    }

    h1 {
      text-align: center;
    }

    .ol-days,
    .ol-days * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    .ol-days {
      width: max-content;
      max-width: 100%;
      margin-inline: auto;

      display: flex;
      flex-direction: column;
      gap: 0.25em;
      font-size: clamp(1rem, 5vw, 2rem);
      color: hsl(0, 0%, 90%);

      list-style: none;
      counter-reset: ol-days-counter;
    }

    .ol-days>li {
      counter-increment: ol-days-counter;
      padding-inline: 1em;
      display: grid;
      grid-template-columns: min-content min-content auto;
      align-items: center;
      background: linear-gradient(hsla(0 0% 100% / 0.2),
          transparent 50%,
          hsla(0 0% 0% / 0.3)),
        var(--clr_bg);
      text-align: right;
      transition: transform 250ms ease;
      cursor: default;
      /* text-shadow: 0 0 5px hsla(0 0% 50% / 0.75); */
    }

    .ol-days>li:hover {
      transform: scale(1.05);
    }

    .ol-days>li::before,
    .ol-days>li::after {
      display: grid;
      align-items: center;
      grid-row: 1;
      text-align: left;
    }

    .ol-days>li::before {
      content: var(--month);
      grid-column: 1;
      padding-inline-end: 0.25em;
    }

    .ol-days>li::after {
      content: counter(ol-days-counter, decimal-leading-zero);
      grid-column: 2;
      width: 1.75em;
      height: 3.5em;
      background-image: linear-gradient(90deg,
          rgba(0, 0, 0, 0.3),
          rgba(0, 0, 0, 0) 25%),
        radial-gradient(circle at 0.125em center,
          var(--clr_accent) 1.25em,
          transparent calc(1.25em + 1px));
      padding-inline-start: 0.1em;
    }

    .ol-days>li:nth-child(even)::before {
      padding-inline-start: 1.25em;
    }

    .ol-days>li:nth-child(6n + 1) {
      --clr_bg: #2e2b3c;
      --clr_accent: #fb6767;
    }

    .ol-days>li:nth-child(6n + 2) {
      --clr_bg: #47505f;
      --clr_accent: #c14755;
    }

    .ol-days>li:nth-child(6n + 3) {
      --clr_bg: #37aa8d;
      --clr_accent: #a1cc6f;
    }

    .ol-days>li:nth-child(6n + 4) {
      --clr_bg: #8fb568;
      --clr_accent: #566574;
    }

    .ol-days>li:nth-child(6n + 5) {
      --clr_bg: #24b8b8;
      --clr_accent: #c4b36a;
    }

    .ol-days>li:nth-child(6n + 6) {
      --clr_bg: #fc6868;
      --clr_accent: #2e2b3c;
    }

    a {
      color: white;
      text-decoration: none;
      /* no underline */
    }
  </style>
  <html>
  <title>Band List</title>

  <body>

    <h1>Listagem de Bandas</h1>
    <ol class="ol-days" style="--month:' '">
      <c:forEach var="band" items="${bands}">
        <li><a href="bands/${band.id}">${band.name}</a></li>
      </c:forEach>
    </ol>

  </body>

  </html>