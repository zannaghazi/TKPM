import * as types from '../constants/ActionType';
var initialState = [];

var myReducer = (state = initialState, action) => {
    console.log(action);
    switch (action.type) {
        case types.SET_USER_LOGIN:
            state = action.user;
            return {...state};
        default:
            return state;

    }
    return state;
}

export default myReducer;