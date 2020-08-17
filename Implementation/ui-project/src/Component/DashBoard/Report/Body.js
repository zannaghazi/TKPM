import React, {useState} from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'

function Body(props) {
    const [show, setShow] = useState(true);
    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={7}>
                        <h3>Lập báo cáo</h3>
                    </Col>
                    <Col xs={3} className = "mt-1 d-flex justify-content-end align-items-center">
                        <span>Tháng</span>
                        <input type="text" className={styles.myInputReport}/>
                        <span>Năm</span>
                        <input type="text" className={styles.myInputReport}/>
                    </Col>
                    <Col xs = {2} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch} onClick={() => setShow(true)}><i className="fa fa-asterisk"></i>&nbsp;Xem báo cáo</Button>
                    </Col>
                </Row>
                <Row className="mt-3">
                    <Col xs={8}>
                        <Row>
                            <TableContainer />
                        </Row>
                        {true > 0 ? <Row className="mt-1 d-flex justify-content-center">
                            <MyTablePagination />
                        </Row>: <div>Không có dữ liệu</div>}
                        
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Body;