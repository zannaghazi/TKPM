import styles from './static/styles.module.css'
import 'font-awesome/css/font-awesome.min.css';
import { Container, Col, Row, Dropdown } from 'react-bootstrap'
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'

function LoginButton(props) {
    const [reload, setReload] = useState(false);
    function renderDropdown() {
        console.log("token",localStorage.librarymanagement_token);
        if (!localStorage.librarymanagement_token || localStorage.librarymanagement_token === "") {
            return (
                <Col lg={9}>
                    <Link to="/Login" className={styles.normalFont}><b>Đăng nhập</b></Link><br /><span className={styles.smallFont}>hoặc đăng ký mới</span>
                </Col>);
        }
        else {
            return (
                <Col lg={9}>
                    <div className={styles.myDropdownHome}>
                        <span>Hi, <b>Hoàng NT</b>!</span> &nbsp;<i class="fa fa-caret-down"></i>
                        <div className={styles.myDropdownContent}>
                        <a className={styles.black} href = "#" onClick = {SignOut}>Quản lý</a><br/>
                        <a className={styles.black} href = "#" onClick = {SignOut}>Đăng xuất</a>
                        </div>
                    </div>
                </Col>
            )
        }
    }

    function SignOut(event){
        localStorage.librarymanagement_token = "";
        setReload(true);
    }
    return (
        <div className={styles.myLoginComponent}>
            <Container>
                <Row>
                    <Col lg={3}>
                        <i className={["fa fa-user-circle fa-2x", styles.myAccount].join(" ")}></i>
                    </Col>
                    {renderDropdown()}
                </Row>
            </Container>
        </div>
    );
}

export default LoginButton;