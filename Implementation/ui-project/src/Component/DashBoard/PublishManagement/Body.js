import React, {useState} from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'

function Body(props) {
    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={9}>
                        <h3>Danh sách nhà xuất bản</h3>
                    </Col>
                    <Col xs = {3} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch} ><i className="fa fa-plus"></i>&nbsp;Thêm nhà xuất bản</Button>
                    </Col>
                </Row>
                <Row className = "mt-3">
                    <TableContainer />
                </Row>
                <Row className = "mt-1 d-flex justify-content-center">
                    <MyTablePagination />
                </Row>
            </Container>
        </div>
    );
}

export default Body;