import { useState } from "react";

import "./styles.css";

function Login({setIsLoginView, isLoginView}) {

    const [errorMessages, setErrorMessages] = useState({});


    const database = [
    {
        username: "typeface",
        password: "1234"
    },
    {
        username: "Mohan",
        password: "1234"
    }
    ];

    const errors = {
    uname: "invalid username",
    pass: "invalid password"
    };

    const handleSubmit = (event) => {
    event.preventDefault();

    var { uname, pass } = document.forms[0];
    const userData = database.find((user) => user.username === uname.value);

    if (userData) {
        if (userData.password !== pass.value) {
        setErrorMessages({ name: "pass", message: errors.pass });
        } else {
            setIsLoginView(false);
            localStorage.setItem("user",uname.value);
        }
    } else {
        setErrorMessages({ name: "uname", message: errors.uname });
    }
    };

    const renderErrorMessage = (name) =>
    name === errorMessages.name && (
        <div className="error">{errorMessages.message}</div>
    );

    const renderForm = (
    <div className="form">
        <form onSubmit={handleSubmit}>
        <div className="input-container">
            <label>Username </label>
            <input type="text" name="uname" required />
            {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
            <label>Password </label>
            <input type="password" name="pass" required />
            {renderErrorMessage("pass")}
        </div>
        <div className="button-container">
            <input type="submit" />
        </div>
        </form>
    </div>
    );

    return (
    <div className="app">
        <div className="view-modal">
        <div className="title">Sign In</div>
        {!isLoginView ? 
        <div>
            <p>User is successfully logged in</p>
            
        </div> 
        : renderForm}
        </div>
    </div>
    );
    }

export default Login;