import styles from './static/styles.module.css'
import 'font-awesome/css/font-awesome.min.css';
import { Container, Col, Row, Dropdown } from 'react-bootstrap'
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'
import { connect } from 'react-redux';

function LoginButton(props) {
    const [reload, setReload] = useState(false);
    function renderDropdown() {
        if (!localStorage.quanlythuvien_accesstoken || localStorage.quanlythuvien_accesstoken === "") {
            return (
                <Col lg={9}>
                    <Link to="/Login" className={styles.normalFont}><b>Đăng nhập</b></Link><br /><span className={styles.smallFont}>hoặc đăng ký mới</span>
                </Col>);
        }
        else {
            return (
                <Col lg={9}>
                    <div className={styles.myDropdownHome}>
                        <span>Hi, <b>{props.userLogin.fullname}</b>!</span> &nbsp;<i className="fa fa-caret-down"></i>
                        <div className={styles.myDropdownContent}>
                        <Link className={styles.black} to="/dashboard" >Quản lý</Link><br/>
                        <a className={styles.black} href = "#" onClick = {SignOut}>Đăng xuất</a>
                        </div>
                    </div>
                </Col>
            )
        }
    }

    function SignOut(event){
        localStorage.quanlythuvien_accesstoken = "";
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

const mapStateToProps = state => {
	return {
		userLogin: state.user_login
	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(LoginButton);