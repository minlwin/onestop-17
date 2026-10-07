import ManagementTemplate from "@/components/widgets/pages/management-template";

export default function CreateUserPage() {
    return (
        <ManagementTemplate page="Create User" links={[
            {name : "User Management", route: "/management/users"}
        ]}>
            <></>
        </ManagementTemplate>
    )
}