import {useEffect, useState} from "react";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {utility} from "../utility.js";


export default function MenuLayout() {
    const navigate = useNavigate();
    const [loggedInUser, setLoggedInUser] = useState(false);
    const path = useLocation().pathname;

    const doLogin = async (e) => {
        e.preventDefault();
        const componentData = new FormData(e.currentTarget);

        if (componentData.get('username-field').length <= 0 || componentData.get('password-field').length <= 0) {
            alert("Fields cannot be empty!");
            return;
        }

        await utility.apiPostWithDictionaryDataType(`/user/login`, {
            'username': componentData.get('username-field'),
            'password': componentData.get('password-field')
        })
            .then(response => {
                if (response.ok) {
                    utility.loggedInUser().then(
                        async d => {
                            await setLoggedInUser(d);
                            navigate("/");
                        }
                    )
                }
            });
    }

    const doLogout = async (e) => {
        e.preventDefault();

        await utility.apiGet(`/user/logout`)
            .then(response => {
                if (response.ok) {
                    setLoggedInUser(false);
                    navigate("/");
                }
            })
    }

    useEffect(() => {
        utility.loggedInUser().then(
            d => {
                if (d === false) {
                    setLoggedInUser(false)
                } else {
                    setLoggedInUser(d);
                }
            }
        );
    }, []);


    const loggedOff = <div className="wrapper">
        <span className="hovertext" data-hover="Home page">
            <Link to={"/"} className="menu-layout-elements">
                <button type="button" className="profile-button" id="home-button"></button>
            </Link>
        </span>
        <div>
            <Link to={"/registration"} className="menu-layout-elements">
                <button type="button">Registration</button>
            </Link>
        </div>
        <div className="first, go-right">
            <form onSubmit={event => doLogin(event)}>
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" name="username-field" minLength="3"/>
                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" name="password-field" minLength="3"/>

                <button type="submit" id="login-button" className="nav-bar-button">Login</button>
            </form>
        </div>

    </div>;

    const houseCrest = () => {
        switch (loggedInUser.house) {
            case "GRYFFINDOR":
                return "gryffindor-house-crest";
            case "SLYTHERIN":
                return "slytherin-house-crest";
            case "HUFFLEPUFF":
                return "hufflepuff-house-crest";
            case "RAVENCLAW":
                return "ravenclaw-house-crest";
            default:
                return "profile-button";
        }
    }

    const loggedIn = <div className="wrapper">
        <span className="hovertext" data-hover="Home page" title="Home page">
            <Link to={"/"} className="menu-layout-elements">
                <button type="button" className="profile-button" id="home-button"></button>
            </Link>
        </span>
        <p className="menu-layout-elements slight-white-background">Welcome {loggedInUser.name}!</p>
        <button onClick={event => doLogout(event)} className="menu-layout-buttons, go-right">Logout
        </button>
        <span className="hovertext" data-hover="Profile page">
            <Link to={"/profile"} className="menu-layout-buttons, go-right">
                <button id="profile-button" type="button" className={houseCrest()} title="Profile"></button>
            </Link>
        </span>
    </div>;

    return loggedInUser === false ? loggedOff : loggedIn;
};
