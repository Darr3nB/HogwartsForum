import {useState} from "react";

const MenuLayout = () => {
    const [isLoggedIn, setLoginState] = useState(false);

    const loggedOff = <div>
        <button type="button">Home</button>
        <div>
            <label htmlFor="username-field">Username: </label>
            <input type="text" id="username-field" minLength="3"/>
            <label htmlFor="password-field">Magic word: </label>
            <input type="text" id="password-field" minLength="3"/>
            <button type="button" id="login-button" className="nav-bar-button">Login</button>
        </div>
        <div>
            <button type="button">Registration</button>
        </div>
    </div>;

    const loggedIn = <div>
        <button type="button">Home</button>
        <p>Welcome USERNAME_HERE!</p>
        <button type="button">Logout</button>
        <button type="button">Profile</button>
    </div>;

    return isLoggedIn ? loggedIn : loggedOff;
};

export default MenuLayout;