import React from 'react';
import { Container, Row } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import UserQueryModal from './Modal/Query/Index'

function Body(props) {
    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <h3>Danh sách tài khoản</h3>
                </Row>
            </Container>
            <UserQueryModal />
        </div>
    );
}

export default Body;