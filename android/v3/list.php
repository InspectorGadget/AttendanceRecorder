<!DOCTYPE html>
<!--
Copyright (C) 2017 RTG
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title> RTGNetwork | List </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="bootstrap.min.css">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <style>
            div.controlled {
                width: 400px;
                height: 200px;
            }
            input {
                text-align: center;
            }
            input:hover {
                color: red;
            }
            table {
                border: 5px;
                margin-right: 50px;
                text-align: center;
            }

            td, tr, th {
                text-align: center;
            }

            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-default">

            <div class="container-fluid">

                <div class="navbar-header">

                    <a class="navbar-brand" href="#"> RTGNetwork </a>

                </div>

                <ul class="nav navbar-nav navbar-left">

                    <li class="active"> <a href="#"> Attendance Recorder </a> </li>

                </ul>

            </div>

        </nav>

        <br><br><br>

    <center>


    <table class="table">
        <thead class="thead-inverse">
            <tr>
                <td>ID</td>
                <td>Username</td>
                <td>Attendance</td>
            </tr>
        </thead>
        <tbody>

        <?php


            $conn = new SQLite3("database.db");
            $statement = "SELECT * FROM `students`";
            $result = $conn->query($statement);
                    

            while ($row = $result->fetchArray(1)) {
                        
                echo "<tr>";
                echo "<td>" . $row['id'] . "</td>";
                echo "<td>" . $row['username'] . "</td>";
                echo "<td>" . $row['attendance'] . "</td>";
                echo "</tr>";

            }

        ?>

            </tbody>
            </table>


    </center>

    </body>

</html>