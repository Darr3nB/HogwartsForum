import {useState} from "react";
import {Link} from "react-router-dom";

const MenuLayout = () => {
    // TODO request from backend if logged in
    const [isLoggedIn, setLoginState] = useState(false);

    const loggedOff = <div>
        <Link to={"/"}>
            <button type="button">Home</button>
        </Link>
        <div>
            <form action="/login" method="post">
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" minLength="3"/>
                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" minLength="3"/>
                <Link to={"/login"}>
                    <button type="button" id="login-button" className="nav-bar-button">Login</button>
                </Link>
            </form>
        </div>
        <div>
            <Link to={"/registration"}>
                <button type="button">Registration</button>
            </Link>
        </div>
    </div>;

    const loggedIn = <div>
        <Link to={"/"}>
            <button type="button">Home</button>
        </Link>
        <p>Welcome USERNAME_HERE!</p>
        <Link to={"/logout"}>
            <button type="button">Logout</button>
        </Link>
        <Link to={"/profile"}>
            <button type="button">Profile</button>
        </Link>
    </div>;

    return isLoggedIn ? loggedIn : loggedOff;
};

export default MenuLayout;
