import MemberTemplate from "@/components/widgets/pages/member-template";

export default function BalanceReportPage() {
    return (
        <MemberTemplate page="Details" links={[
            {name : "Balance Report", route : "/member/balances"}
        ]}>
            <></>
        </MemberTemplate>
    )
}