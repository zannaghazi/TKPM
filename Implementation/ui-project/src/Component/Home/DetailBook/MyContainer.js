import React, {useState, useEffect} from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import config from '../../../asset/config.json'
import styles from './static/styles.module.css';
import Info from './Info'
import { useParams } from 'react-router-dom';

function MyContainer(props) {
    const [data, setData] = useState({});
    const params = useParams();

    useEffect(() => {
        let url = config.severAPi.hostUrl + ":8081/book/get_book_info_by_isbn/" + params.isbn;
        fetch(url, {
            method: "get",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
        })
            .then(res => res.json())
            .then(dataReceive => {
                console.log("111111111111111", dataReceive)
                setData(dataReceive);
            });
    }, []);

    return (
        <Row className={styles.fullWidth}>
            <Col sm={3}>
                <img src={config.severAPi.hostUrl + ":8081" + data.path} alt="img" className={["d-block w-100", styles.myCarousel].join(" ")} />
            </Col>
            <Col sm={9}>
                <Info data={data} />
            </Col>
        </Row>
    );
}

export default MyContainer;