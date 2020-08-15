import React from 'react';
import styles from './static/styles.module.css'
import { useHistory, Link } from "react-router-dom";
import { connect } from 'react-redux';

function Header(props) {
    const history = useHistory();
    function SignOut(event){
        localStorage.quanlythuvien_accesstoken = "";
        history.push("/");
    }
    return (
        <nav className={["navbar navbar-expand-lg navbar-light bg-light my-nav", styles.myNav].join(" ")}>
            <div className="col-sm-1" />
            <div className={["col-sm-3"].join(" ")}><Link to ="/" className={styles.companyName}>Zan Library</Link></div>
            <div className="col-sm-6"></div>
            <div className="col-sm-2 d-flex justify-content-end" >
                <div className={[styles.myDropdownHome].join(" ")}>
                    <i className={["fa fa-user-circle fa-2x", styles.logoAccount].join(" ")}></i>
                    <span className={[styles.white].join(" ")}> <b>{props.userLogin.fullname}</b>!&nbsp;<i className="fa fa-caret-down"></i></span> 
                    <div className={styles.myDropdownContent}>
                        <a className={styles.black} href="#" onClick={SignOut}>Đăng xuất</a>
                    </div>
                </div>
            </div>
        </nav>
    );
}

const mapStateToProps = state => {
    return {
        userLogin: state.user_login
    }
}

const mapDispatchToProps = (dispatch, props) => {
    return {
        
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);