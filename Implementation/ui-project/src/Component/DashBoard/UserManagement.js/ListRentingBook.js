    import React from 'react';
    import { Container, Row, Card, Button } from 'react-bootstrap';
    import styles from '../static/styles.module.css'
    import { useEffect, useState } from 'react';
    import config from '../../../asset/config.json'

    function ListRentingBook(props) {
        const [items, setItems] = useState([]);

        function receiveBook(data) {
            let body = {
                rentingID: data.rentingID,
                ID: data.ID
            }

            let url = config.severAPi.hostUrl + ":8081/book/return_book/";
            fetch(url, {
                method: "post",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
                body: JSON.stringify(body)
            })
                .then(res => res.json())
                .then(dataReceive => {
                    alert(dataReceive.message);
                    if (dataReceive.result) {
                        props.byRemove(data)
                    }
                });
        }

        function compensationBook(data) {
            let body = {
                rentingID: data.rentingID,
                ID: data.ID
            }

            let url = config.severAPi.hostUrl + ":8081/book/repay_book/";
            fetch(url, {
                method: "post",
                headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
                body: JSON.stringify(body)
            })
                .then(res => res.json())
                .then(dataReceive => {
                    alert(dataReceive.message);
                    if (dataReceive.result) {
                        props.byRemove(data)
                    }
                });
        }

        useEffect(() => {
            const listItems = props.list.map((element, index) => {

                return (<Card className={["mt-3", styles.widthNightyPercent].join(" ")} key={index}>
                    <Card.Header>{element.ID}</Card.Header>
                    <Card.Body>
                        <Card.Title>{element.ISBN}</Card.Title>
                        <Card.Text>
                            {element.name}
                        </Card.Text>
                        <Button variant="primary" onClick={() => receiveBook(element)}><i className="fa fa-angle-double-left"></i>&nbsp;Trả sách</Button>
                        <Button variant="danger" onClick={() => compensationBook(element)}><i className="fa fa-exclamation"></i>&nbsp;Đền sách</Button>
                    </Card.Body>
                </Card>)

            })

            setItems(listItems);
        }, [props.list]);
        return (
            <Container className={styles.myBorderLeft}>
                <Row className={["ml-3", styles.myMarginBottom].join(" ")}>
                    <h5> Danh sách sách mượn </h5>
                </Row>
                <Row className="ml-3">
                    {items}
                </Row>
            </Container>
        );
    }

    export default ListRentingBook;