<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Область</title>
  <link rel="stylesheet" href="styles/style.css">
  <script src="scripts.js"></script>
</head>
<body onload="drawCanvas('no')">
<header>
  <p>Кубикова Екатерина Алексеевна</p>
  <p>P3200</p>
  <p>100027</p>
</header>
<table>
  <caption>Испытай свою удачу</caption>
  <tr>
    <th colspan="2" align="left">
      Введите координаты точки и радиус
    </th>
    <td>
      <label id="clock"></label>
    </td>
  </tr>
  <tr>
    <td id="areas" width="30%" align="center">
      <div id="support">
        <noscript>
          <br><br>
          <img src='area.jpg' alt='Область' width='420' height='380'>
          <p class="warning-text">Включите, пожалуйста, JavaScript в браузере. Вы сможете тыкать на область :)</p>
        </noscript>
      </div>
      <canvas id="canvas" onclick="clickOnArea()"></canvas>
    </td>
    <td>
      <form id="form" method="get" action="control" target="response" onsubmit="return onFormSubmit();">
        <table>
          <tr>
            <td align="center">
              <label class="coordinate" for="x"> Координата X: <br><br>
                <select name="x" id="x">
                  <option selected value="no"> Не выбрано </option>
                  <option value="-4"> -4 </option>
                  <option value="-3"> -3 </option>
                  <option value="-2"> -2 </option>
                  <option value="-1"> -1 </option>
                  <option value="0">  0  </option>
                  <option value="1">  1  </option>
                  <option value="2">  2  </option>
                  <option value="3">  3  </option>
                  <option value="4">  4  </option>
                </select>
              </label>
              <p class="warning-text" hidden>Не выбрана координата X.</p>
            </td>
            <td width="60%" align="center" rowspan="5">
              <iframe name="response" id="response"></iframe>
            </td>
          </tr>
          <tr>
            <td align="center">
              <label class="coordinate" for="y"> Координата Y:  <br><br>
                <input id="y" name="y" type="text" maxlength="17" placeholder="(-5; 5)"  autocomplete="off">
              </label>
              <p class="warning-text" hidden>Введите число.</p>
              <p class="warning-text" hidden>Координата Y должна находиться в пределах от -5 до 5.</p>
            </td>
          </tr>
          <tr>
            <td align="center">
              <label class="coordinate" for="r"> Радиус R: <br><br>
                <select name="r" id="r" onchange="drawCanvas(document.getElementById('r').value)">
                  <option selected value="no"> Не выбрано </option>
                  <option value="1"> 1 </option>
                  <option value="1.5"> 1.5 </option>
                  <option value="2"> 2 </option>
                  <option value="2.5"> 2.5 </option>
                  <option value="3">  3  </option>
                </select>
              </label>
              <p class="warning-text" hidden>Не выбран радиус R.</p>
            </td>
          </tr>
          <tr>
            <td>
              <table>
                <tr>
                  <td align="center">
                    <input type="submit" value="Проверить" id="submit">
                  </td>
                  <td>
                    <input type="reset" value="Сбросить" id="reset" onclick="return clearForm(this.form);">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </form>
    </td>
  </tr>
</table>
<script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js"></script>

<script>
  function clock() {
    let date = new Date(),
            hours = (date.getHours() < 10) ? '0' + date.getHours() : date.getHours(),
            minutes = (date.getMinutes() < 10) ? '0' + date.getMinutes() : date.getMinutes(),
            seconds = (date.getSeconds() < 10) ? '0' + date.getSeconds() : date.getSeconds();
    document.getElementById('clock').innerHTML = hours + ':' + minutes + ':' + seconds;
  }
  setInterval(clock, 1000);
  clock();

  var warningX = document.getElementsByClassName("warning-text")[0];
  var warningYFormat = document.getElementsByClassName("warning-text")[1];
  var warningYValue = document.getElementsByClassName("warning-text")[2];
  var warningR = document.getElementsByClassName("warning-text")[3];
  var xValid = false;
  var yValid = false;
  var rValid = false;
  function clearForm() {
    warningX.hidden = true;
    let yField = document.getElementById("y");
    yField.classList.remove("warning-field");
    warningYValue.hidden = true;
    warningYFormat.hidden = true;
    warningR.hidden = true;
  }
</script>
</body>
</html>