import React, { useState } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'
import { useHistory } from "react-router-dom";
function Body(props) {
    const history = useHistory();
    function NewAuthor(event) {
        history.push("/dashboard/new_author");
    }
    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={9}>
                        <h3>Danh sách tác giả</h3>
                    </Col>
                    <Col xs={3} className="d-flex justify-content-end">
                        <Button variant="success" className={styles.myButtonSearch} onClick={NewAuthor} ><i className="fa fa-plus"></i>&nbsp;Thêm tác giả</Button>
                    </Col>
                </Row>
                <Row className="mt-3">
                    <Col xs={10}>
                        <Row>
                            <TableContainer />
                        </Row>
                        <Row className="mt-1 d-flex justify-content-center">
                            <MyTablePagination />
                        </Row>
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Body;