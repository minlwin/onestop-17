import MemberTemplate from "@/components/widgets/pages/member-template";

export default function EmployeeCreationPage() {
    return (
        <MemberTemplate page="Create" links={[
            {name : "Employee Management", route : "/member/organization/employee"}
        ]}>
            <></>
        </MemberTemplate>
    )
}