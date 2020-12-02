<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="asp.netloginpage.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style>
        body{
            background-color: #607d8b;
        }

        .table-wrapper {
            margin: auto;
            text-align:center;
            background-color: #ffffff;
            border-radius: 5px;
            border: 1px solid #808080;
            max-width: 400px;
           margin-top: 15%;
            font-size: 25px;
        }

        .column {
            display: flex;
            flex-direction: column
        }

        .row span {
            font-size: 13px;
            font-weight: bold;
        }

        .row input {
            height: 30px;
            width: 85%;
            border: 1px solid #e1e1e1;
            padding: 12px 26px
        }

        .row {
                margin-bottom: 20px;
        }

        input[type=submit] {
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #e1e1e1;
            padding: 12px 26px;

        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
    <div class="table-wrapper">
        <p>Welcome!</p>
        <div class="column">
              <div class="row">
                    <asp:TextBox ID="txtUserName" runat="server" placeholder="Enter the username"></asp:TextBox>
                </div>
                <div class="row">
                    <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" placeholder="Enter the password"></asp:TextBox>
                </div>
        </div>
        <asp:Button ID="btnLogin" runat="server" Text="Login" OnClick="btnLogin_Click" />

        <asp:Label ID="lblErrorMessage" runat="server" Text="Incorrect User Credentials" ForeColor="Red"></asp:Label>
    </table>
    </div>
    </form>
</body>
</html>