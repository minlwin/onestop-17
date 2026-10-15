import MemberTemplate from "@/components/widgets/pages/member-template";

export default function EmployeeDetailsPage() {
    return (
        <MemberTemplate page="Details" links={[
            {name : "Employee Management", route : "/member/organization/employee"}
        ]}>
            <></>
        </MemberTemplate>
    )
}