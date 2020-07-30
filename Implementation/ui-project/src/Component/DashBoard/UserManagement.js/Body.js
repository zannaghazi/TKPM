import React, {useState} from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import UserQueryModal from './Modal/Query/Index'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'

function Body(props) {
    const [show, setShow] = useState(true);
    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={9}>
                        <h3>Danh sách tài khoản</h3>
                    </Col>
                    <Col xs = {3} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch} onClick={() => setShow(true)}><i className="fa fa-search"></i>&nbsp;Tìm kiếm khác</Button>
                    </Col>
                </Row>
                <Row className = "mt-3">
                    <TableContainer />
                </Row>
                <Row className = "mt-1 d-flex justify-content-center">
                    <MyTablePagination />
                </Row>
            </Container>
            <UserQueryModal show = {show} bindEventShow = {setShow}/>
        </div>
    );
}

export default Body;