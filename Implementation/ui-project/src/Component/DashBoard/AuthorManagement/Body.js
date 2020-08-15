import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'
import { useHistory } from "react-router-dom";
import { connect } from 'react-redux';
import config from '../../../asset/config.json'

function Body(props) {
    const history = useHistory();
    const [active, setActive] = useState(1);
    const [visibleList, setVisibleList] = useState([]);

    useEffect(() => {
        let temp =[];
        let limit = 0;
        if((active-1) * config.pagination.limit + config.pagination.limit > props.list.length){
            limit = props.list.length;
        }
        else{
            limit = (active-1) * config.pagination.limit + config.pagination.limit;
        }
        
		for(let i = (active-1) * config.pagination.limit; i<limit; i++){
            temp.push(props.list[i]);
        }
        console.log("data", props.list);
        setVisibleList(temp);
	}, [props.list, active]);

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
                            <TableContainer listAuthor = {visibleList} />
                        </Row>
                        {props.list.length > 0 ? <Row className="mt-1 d-flex justify-content-center">
                            <MyTablePagination listAuthor = {props.list} active={active} setActive={setActive}/>
                        </Row>: <div>Không có dữ liệu</div>}
                        
                    </Col>
                </Row>
            </Container>
        </div>
    );
}

const mapStateToProps = state => {
	return {
		list: state.authors,
	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);