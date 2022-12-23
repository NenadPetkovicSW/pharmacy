import http from "../http-common";

class UserRolesDataService {
    getAll() {
        return http.get("/rolesData");
    }

    
    get(id) {
        return http.get(`/rolesData/${id}`);
    }

    create(data) {
        return http.post("/rolesData", data);
    }

    getUserRole(userId)
    {
        return http.get(`/rolesData/user/${userId}`);
    }

    update(id, data) {
        return http.put(`/rolesData/${id}`, data);
    }

    delete(id) {
        return http.delete(`/rolesData/${id}`);
    }
}

export default new UserRolesDataService();