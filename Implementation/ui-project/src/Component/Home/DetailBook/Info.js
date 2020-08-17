import React, { useState, useEffect } from 'react';
import { Card, Container, Row, Col } from 'react-bootstrap';
import styles from './static/styles.module.css'
import Moment from 'react-moment';
import TableContainer from './TableContainer'

function Info(props) {
    const [data, setData] = useState({});

    useEffect(() => {
        setData(props.data)
        let date = new Date(props.data.releaseDate);
    }, [props.data]);

    return (
        <Card className={styles.myCardContainer}>
            <Card.Body>
                <Container>
                    <Row className={[styles.myMarginBottom, "pb-5", "mt-5"].join(" ")}>
                        <h3><b>{data.name}</b></h3>
                    </Row>
                    <Row>
                        <Col xs={5}>
                            <Row className="mt-5">
                                <h5><b>ISBN:&nbsp;</b>{data.ISBN}</h5>
                            </Row>
                            <Row className="mt-3">
                                <h5><b>Vị trí:&nbsp;</b>{data.location}</h5>
                            </Row>
                            <Row className="mt-3">
                                <h5><b>Thể loại:&nbsp;</b>{data.typeName}</h5>
                            </Row>
                            <Row className="mt-3">
                                <h5><b>Tác giả:&nbsp;</b>{data.author}</h5>
                            </Row>
                            <Row className="mt-3">
                                <h5><b>Nhà xuất bản:&nbsp;</b>{data.publisher}</h5>
                            </Row>
                            <Row className="mt-3">
                                <h5><b>Ngày phát hành:&nbsp;</b><Moment format="DD/MM/YYYY">
                                    {data.releaseDate}
                                </Moment></h5>
                            </Row>

                        </Col>
                        <Col xs={7}>
                            <TableContainer ISBN={data.ISBN}/>
                        </Col>
                    </Row>
                </Container>
            </Card.Body>
        </Card>
    );
}

export default Info;