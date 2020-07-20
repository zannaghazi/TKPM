import React from 'react';
import styles from './static/styles.module.css'
import 'font-awesome/css/font-awesome.min.css';
import { Container, Col, Row } from 'react-bootstrap'

function LoginButton(props) {
    return (
        <div className={styles.myLoginComponent}>
            <Container>
                <Row>
                    <Col lg={3}>
                        <i className={["fa fa-user-circle fa-2x", styles.myAccount].join(" ")}></i>
                    </Col>
                    <Col lg={9}>
                        <a href="/account/login"><b>Đăng nhập</b></a><br /><span className={styles.smallFont}>hoặc đăng ký mới</span>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default LoginButton;