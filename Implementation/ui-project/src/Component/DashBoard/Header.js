import React from 'react';
import styles from './static/styles.module.css'
import { useHistory } from "react-router-dom";

function Header(props) {
    const history = useHistory();
    function SignOut(event){
        localStorage.librarymanagement_token = "";
        history.push("/");
    }
    return (
        <nav className={["navbar navbar-expand-lg navbar-light bg-light my-nav", styles.myNav].join(" ")}>
            <div className="col-sm-1" />
            <div className={["col-sm-3", styles.companyName].join(" ")}>Zan Library</div>
            <div className="col-sm-6"></div>
            <div className="col-sm-2 d-flex justify-content-end" >
                <div className={[styles.myDropdownHome].join(" ")}>
                    <i className={["fa fa-user-circle fa-2x", styles.logoAccount].join(" ")}></i>
                    <span className={[styles.white].join(" ")}> <b>Hoàng NT</b>!&nbsp;<i className="fa fa-caret-down"></i></span> 
                    <div className={styles.myDropdownContent}>
                        <a className={styles.black} href="#" onClick={SignOut}>Đăng xuất</a>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Header;