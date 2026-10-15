import MemberTemplate from "@/components/widgets/pages/member-template";

export default function EditLedgerPage() {
    return (
        <MemberTemplate page="Edit" links={[
            {name : "Ledger Management", route : "/member/organization/ledger"}
        ]}>
            <></>
        </MemberTemplate>
    )
}