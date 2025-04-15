// 模拟用户数据库
const users = [
    {
        id: 1,
        email: 'student@example.com',
        password: 'student123',
        name: 'Student User',
        role: 'student'
    },
    {
        id: 2,
        email: 'teacher@example.com',
        password: 'teacher123',
        name: 'Teacher User',
        role: 'teacher'
    },
    {
        id: 3,
        email: 'admin@example.com',
        password: 'admin123',
        name: 'Admin User',
        role: 'admin'
    }
];

// 模拟API响应延迟
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export default {
    async login({ email, password, role }) {
        await delay(500); // 模拟网络延迟

        const user = users.find(u =>
            u.email === email &&
            u.password === password &&
            u.role === role
        );

        if (!user) {
            throw new Error('Invalid email, password or role');
        }

        return {
            user: {
                id: user.id,
                email: user.email,
                name: user.name,
                role: user.role
            },
            token: 'mock-jwt-token'
        };
    },

    async register({ email, password, name, role }) {
        await delay(500); // 模拟网络延迟

        if (users.some(u => u.email === email)) {
            throw new Error('Email already exists');
        }

        const newUser = {
            id: users.length + 1,
            email,
            password,
            name,
            role
        };

        users.push(newUser);

        return {
            user: {
                id: newUser.id,
                email: newUser.email,
                name: newUser.name,
                role: newUser.role
            },
            token: 'mock-jwt-token'
        };
    }
};