import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'
import MySelectedBox from './MySelectedBox'
import config from '../../../asset/config.json'

function Body(props) {
    const [type, setType] = useState("0");
    const [month, setMonth] = useState("1");
    const [year, setYear] = useState("1990");
    const [listData, setListData] = useState([]);
    const [visibleList, setVisibleList] = useState([]);
    const [active, setActive] = useState(1);

    function search(event) {
        let typeSearch = "rent";
        switch (type) {
            case '0': typeSearch = "rent";
                break;
            case '1': typeSearch = "return"
                break;
            case '2': typeSearch = "input"
                break;
            case '3': typeSearch = "lost"
                break;
            case '4': typeSearch = "selled"
                break;
            default: typeSearch = "rent";
                break;
        }
        if (isNaN(year) || year == "" || isNaN(month) || month == "") {
            alert("Dữ liệu nhập vào không hợp lệ");
            return;
        }
        let body = {
            type: typeSearch,
            time: year + "-" + month
        }
        console.log(body);
        fetch(config.severAPi.hostUrl + ":8081/book/report", {
            method: "post",
            headers: { "Content-Type": "application/json", "x-access-token": localStorage.quanlythuvien_accesstoken },
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(data => {
                console.log("result", data);
                setListData(data.data)
            });
    }

    useEffect(() => {
        let temp = [];
        let limit = 0;
        if ((active - 1) * config.pagination.limit + config.pagination.limit > listData.length) {
            limit = listData.length;
        }
        else {
            limit = (active - 1) * config.pagination.limit + config.pagination.limit;
        }

        for (let i = (active - 1) * config.pagination.limit; i < limit; i++) {
            temp.push(listData[i]);
        }
        console.log("data", listData);
        setVisibleList(temp);
    }, [listData, active]);

    function handleChangeMonth(event) {
        console.log(event.target.value);
        setMonth(event.target.value);
    }

    function handleChangeYear(event) {
        setYear(event.target.value);
    }


    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={3}>
                        <h3>Lập báo cáo</h3>
                    </Col>
                    <Col xs={7} className="mt-1 d-flex justify-content-end align-items-center">
                        <span>Loại</span>
                        <MySelectedBox byValue={setType} />
                        <span>Tháng</span>
                        <input type="text" className={styles.myInputReport} onChange={handleChangeMonth} value={month} />
                        <span>Năm</span>
                        <input type="text" className={styles.myInputReport} onChange={handleChangeYear} value={year} />
                    </Col>
                    <Col xs={2} className="d-flex justify-content-end">
                        <Button variant="success" className={styles.myButtonSearch} onClick={search}><i className="fa fa-eye"></i>&nbsp;Xem báo cáo</Button>
                    </Col>
                </Row>
                <Row className="mt-3">
                    <Col xs={8}>
                        <Row>
                            <TableContainer listBook={visibleList} />
                        </Row>
                        {listData.length > 0 ? <Row className="mt-1 d-flex justify-content-center">
                            <MyTablePagination listBook={listData} active={active} setActive={setActive} />
                        </Row> : <div>Không có dữ liệu</div>}

                    </Col>
                </Row>
            </Container>
        </div>
    );
}

export default Body;