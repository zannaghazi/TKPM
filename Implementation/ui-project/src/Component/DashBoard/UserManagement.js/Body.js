import React, {useState, useEffect} from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import styles from '../static/styles.module.css'
import UserQueryModal from './Modal/Query/Index'
import TableContainer from './TableContainer'
import MyTablePagination from './MyTablePagination'
import { connect } from 'react-redux';
import config from '../../../asset/config.json'

function Body(props) {
    const [show, setShow] = useState(true);
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


    return (
        <div>
            <Container className={[styles.maxWidth, "mt-3"].join(" ")}>
                <Row className={styles.myMarginBottom}>
                    <Col xs={9}>
                        <h3>Danh sách độc giả</h3>
                    </Col>
                    <Col xs = {3} className="d-flex justify-content-end">
                    <Button variant="success" className={styles.myButtonSearch} onClick={() => setShow(true)}><i className="fa fa-search"></i>&nbsp;Tìm kiếm khác</Button>
                    </Col>
                </Row>
                <Row className = "mt-3">
                    <TableContainer listReader = {visibleList}/>
                </Row>
                {props.list.length > 0 ? <Row className = "mt-1 d-flex justify-content-center">
                    <MyTablePagination listAccount = {props.list} active={active} setActive={setActive}/>
                </Row>: <div>Không có dữ liệu</div>}
            </Container>
            <UserQueryModal show = {show} bindEventShow = {setShow}/>
        </div>
    );
}

const mapStateToProps = state => {
	return {
		list: state.readers,
	}
}

const mapDispatchToProps = (dispatch, props) => {
	return {
		
	}
}

export default connect(mapStateToProps, mapDispatchToProps)(Body);